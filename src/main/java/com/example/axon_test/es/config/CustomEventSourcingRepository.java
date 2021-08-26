package com.example.axon_test.es.config;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.common.caching.Cache;
import org.axonframework.common.lock.Lock;
import org.axonframework.common.lock.LockFactory;
import org.axonframework.common.lock.PessimisticLockFactory;
import org.axonframework.eventhandling.DomainEventMessage;
import org.axonframework.eventsourcing.*;
import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.axonframework.messaging.annotation.HandlerDefinition;
import org.axonframework.messaging.annotation.ParameterResolverFactory;
import org.axonframework.messaging.unitofwork.CurrentUnitOfWork;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.modelling.command.LockAwareAggregate;
import org.axonframework.modelling.command.RepositoryProvider;
import org.axonframework.modelling.command.inspection.AggregateModel;

import java.time.Instant;
import java.util.Map;
import java.util.function.Predicate;

import static org.axonframework.common.BuilderUtils.assertNonNull;

/**
 * @author xin
 */
@Slf4j
public class CustomEventSourcingRepository<T> extends EventSourcingRepository<T> {

    private final CustomEmbeddedEventStore eventStore;
    private final SnapshotTriggerDefinition snapshotTriggerDefinition;
    private final AggregateFactory<T> aggregateFactory;
    private final LockFactory lockFactory;

    public static <T> Builder<T> builder(Class<T> aggregateType) {
        return new Builder<>(aggregateType);
    }

    protected CustomEventSourcingRepository(Builder<T> builder) {
        super(builder);
        this.eventStore = builder.eventStore;
        this.aggregateFactory = builder.buildAggregateFactory();
        this.snapshotTriggerDefinition = builder.snapshotTriggerDefinition;
        this.lockFactory = builder.lockFactory;
    }

    protected EventSourcedAggregate<T> doLoadWithLock(String aggregateIdentifier, Instant timestamp) {
        DomainEventStream eventStream = eventStore.readEvents(aggregateIdentifier, timestamp);

        SnapshotTrigger trigger = snapshotTriggerDefinition.prepareTrigger(aggregateFactory.getAggregateType());
        if (!eventStream.hasNext()) {
            throw new AggregateNotFoundException(aggregateIdentifier, "The aggregate was not found in the event store");
        }
        EventSourcedAggregate<T> aggregate = EventSourcedAggregate
                .initialize(aggregateFactory.createAggregateRoot(aggregateIdentifier, eventStream.peek()),
                        aggregateModel(), eventStore, trigger);
        aggregate.initializeState(eventStream);
        if (aggregate.isDeleted()) {
            throw new AggregateDeletedException(aggregateIdentifier);
        }
        return aggregate;
    }

    protected LockAwareAggregate<T, EventSourcedAggregate<T>> doLoad(String aggregateIdentifier, Instant timestamp) {
        Lock lock = lockFactory.obtainLock(aggregateIdentifier);
        try {
            final EventSourcedAggregate<T> aggregate = doLoadWithLock(aggregateIdentifier, timestamp);
            CurrentUnitOfWork.get().onCleanup(u -> lock.release());
            return new LockAwareAggregate<>(aggregate, lock);
        } catch (Exception ex) {
            log.debug("Exception occurred while trying to load an aggregate. Releasing lock.", ex);
            lock.release();
            throw ex;
        }
    }

    public LockAwareAggregate<T, EventSourcedAggregate<T>> load(String aggregateIdentifier, Instant timestamp) {

        if (timestamp == null) {
            return this.load(aggregateIdentifier);
        }

        UnitOfWork<?> uow = CurrentUnitOfWork.get();
        Map<String, LockAwareAggregate<T, EventSourcedAggregate<T>>> aggregates = managedAggregates(uow);
        LockAwareAggregate<T, EventSourcedAggregate<T>> aggregate = aggregates.computeIfAbsent(aggregateIdentifier,
                s -> doLoad(aggregateIdentifier, timestamp));
        uow.onRollback(u -> aggregates.remove(aggregateIdentifier));
        prepareForCommit(aggregate);

        return aggregate;
    }

    public static class Builder<T> extends EventSourcingRepository.Builder<T> {
        private LockFactory lockFactory = PessimisticLockFactory.usingDefaults();
        protected CustomEmbeddedEventStore eventStore;
        protected SnapshotTriggerDefinition snapshotTriggerDefinition = NoSnapshotTriggerDefinition.INSTANCE;
        private AggregateFactory<T> aggregateFactory;
        protected RepositoryProvider repositoryProvider;
        protected Cache cache;
        protected Predicate<? super DomainEventMessage<?>> eventStreamFilter;


        protected Builder(Class<T> aggregateType) {
            super(aggregateType);
        }

        @Override
        public CustomEventSourcingRepository.Builder<T> parameterResolverFactory(ParameterResolverFactory parameterResolverFactory) {
            super.parameterResolverFactory(parameterResolverFactory);
            return this;
        }

        @Override
        public CustomEventSourcingRepository.Builder<T> handlerDefinition(HandlerDefinition handlerDefinition) {
            super.handlerDefinition(handlerDefinition);
            return this;
        }

        @Override
        public CustomEventSourcingRepository.Builder<T> aggregateModel(AggregateModel<T> aggregateModel) {
            super.aggregateModel(aggregateModel);
            return this;
        }


        @Override
        public CustomEventSourcingRepository.Builder<T> lockFactory(LockFactory lockFactory) {
            super.lockFactory(lockFactory);
            this.lockFactory = lockFactory;
            return this;
        }


        public CustomEventSourcingRepository.Builder<T> eventStore(CustomEmbeddedEventStore eventStore) {
            assertNonNull(eventStore, "EventStore may not be null");
            super.eventStore(eventStore);
            this.eventStore = eventStore;
            return this;
        }


        @Override
        public CustomEventSourcingRepository.Builder<T> snapshotTriggerDefinition(SnapshotTriggerDefinition snapshotTriggerDefinition) {
            assertNonNull(snapshotTriggerDefinition, "SnapshotTriggerDefinition may not be null");
            super.snapshotTriggerDefinition(snapshotTriggerDefinition);
            this.snapshotTriggerDefinition = snapshotTriggerDefinition;
            return this;
        }


        @Override
        public CustomEventSourcingRepository.Builder<T> aggregateFactory(AggregateFactory<T> aggregateFactory) {
            assertNonNull(aggregateFactory, "AggregateFactory may not be null");
            super.aggregateFactory(aggregateFactory);
            this.aggregateFactory = aggregateFactory;
            return this;
        }

        @Override
        public CustomEventSourcingRepository.Builder<T> repositoryProvider(RepositoryProvider repositoryProvider) {
            super.repositoryProvider(repositoryProvider);
            this.repositoryProvider = repositoryProvider;
            return this;
        }

        @Override
        public CustomEventSourcingRepository.Builder<T> cache(Cache cache) {
            super.cache(cache);
            this.cache = cache;
            return this;
        }

        @Override
        public CustomEventSourcingRepository.Builder<T> eventStreamFilter(Predicate<? super DomainEventMessage<?>> filter) {
            super.eventStreamFilter(eventStreamFilter);
            this.eventStreamFilter = filter;
            return this;
        }

        @Override
        public CustomEventSourcingRepository.Builder<T> filterByAggregateType() {
            final String aggregateType = buildAggregateModel().type();
            return eventStreamFilter(event -> aggregateType.equals(event.getType()));
        }

        @Override
        @SuppressWarnings("unchecked")
        public <R extends EventSourcingRepository<T>> R build() {
            if (cache != null) {
                log.warn("do not support caching repository!!");
            }
            return (R) new CustomEventSourcingRepository<>(this);
        }

        private AggregateFactory<T> buildAggregateFactory() {
            if (aggregateFactory == null) {
                return new GenericAggregateFactory<>(buildAggregateModel());
            } else {
                return aggregateFactory;
            }
        }

        @Override
        protected void validate() {
            super.validate();
            assertNonNull(eventStore, "The EventStore is a hard requirement and should be provided");
            if (aggregateFactory == null) {
                assertNonNull(
                        aggregateType,
                        "No AggregateFactory is set, whilst either it or the aggregateType is a hard requirement"
                );
                return;
            }
            assertNonNull(
                    aggregateFactory,
                    "No aggregateType is set, whilst either it or the AggregateFactory is a hard requirement"
            );
        }
    }
}