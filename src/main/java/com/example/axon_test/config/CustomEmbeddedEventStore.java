package com.example.axon_test.config;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.common.AxonConfigurationException;
import org.axonframework.common.BuilderUtils;
import org.axonframework.eventhandling.DomainEventMessage;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventsourcing.eventstore.DomainEventStream;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.IteratorBackedDomainEventStream;
import org.axonframework.monitoring.MessageMonitor;

import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.axonframework.common.BuilderUtils.assertNonNull;

/**
 * 让 Aggregate 可以查询历史状态
 * @author xin
 */
@Slf4j
public class CustomEmbeddedEventStore extends EmbeddedEventStore {

    public CustomEmbeddedEventStore(Builder builder) {
        super(builder);
    }

    public static CustomEmbeddedEventStore.Builder builder() {
        return new CustomEmbeddedEventStore.Builder();
    }

    public DomainEventStream readEvents(String aggregateIdentifier, Instant timestamp) {
        Optional<DomainEventMessage<?>> optionalSnapshot;
        try {
            optionalSnapshot = storageEngine().readSnapshot(aggregateIdentifier);
        } catch (Exception | LinkageError e) {
            log.warn("Error reading snapshot. Reconstructing aggregate from entire event stream.", e);
            optionalSnapshot = Optional.empty();
        }
        DomainEventStream eventStream;
        // 加上时间判断，如果 snapshot 在指定的时间之间，那么可以使用，否则直接读取所有的 events
        if (optionalSnapshot.isPresent() && optionalSnapshot.get().getTimestamp().compareTo(timestamp) <= 0) {
            DomainEventMessage<?> snapshot = optionalSnapshot.get();
            eventStream = DomainEventStream.concat(DomainEventStream.of(snapshot),
                    storageEngine().readEvents(aggregateIdentifier,
                            snapshot.getSequenceNumber() + 1));
        } else {
            eventStream = storageEngine().readEvents(aggregateIdentifier);
        }

        eventStream = new IteratorBackedDomainEventStream(eventStream.asStream().filter(m -> m.getTimestamp().compareTo(timestamp) <= 0).iterator());

        Stream<? extends DomainEventMessage<?>> domainEventMessages = stagedDomainEventMessages(aggregateIdentifier);
        return DomainEventStream.concat(eventStream, DomainEventStream.of(domainEventMessages));
    }

    /**
     * 这里为了构建自己的 eventStore 就把 builder 搬过来了，略去了内部实现，具体可以看源码
     */
    public static class Builder extends EmbeddedEventStore.Builder {

        @Override
        public CustomEmbeddedEventStore.Builder storageEngine(EventStorageEngine storageEngine) {
            super.storageEngine(storageEngine);
            return this;
        }

        @Override
        public CustomEmbeddedEventStore.Builder messageMonitor(MessageMonitor<? super EventMessage<?>> messageMonitor) {
            super.messageMonitor(messageMonitor);
            return this;
        }


        @Override
        public CustomEmbeddedEventStore.Builder cachedEvents(int cachedEvents) {
            super.cachedEvents(cachedEvents);
            return this;
        }


        @Override
        public CustomEmbeddedEventStore.Builder fetchDelay(long fetchDelay) {
            super.fetchDelay(fetchDelay);
            return this;
        }


        @Override
        public CustomEmbeddedEventStore.Builder cleanupDelay(long cleanupDelay) {
            super.cleanupDelay(cleanupDelay);
            return this;
        }


        @Override
        public CustomEmbeddedEventStore.Builder timeUnit(TimeUnit timeUnit) {
            super.timeUnit(timeUnit);
            return this;
        }


        @Override
        public CustomEmbeddedEventStore.Builder threadFactory(ThreadFactory threadFactory) {
            super.threadFactory(threadFactory);
            return this;
        }

        @Override
        public CustomEmbeddedEventStore.Builder optimizeEventConsumption(boolean optimizeEventConsumption) {
            super.optimizeEventConsumption(optimizeEventConsumption);
            return this;
        }

        @Override
        public CustomEmbeddedEventStore build() {
            return new CustomEmbeddedEventStore(this);
        }

        @Override
        protected void validate() throws AxonConfigurationException {
            super.validate();
        }
    }
}