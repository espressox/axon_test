package com.example.axon_test.es.config;

import org.axonframework.common.AxonConfigurationException;
import org.axonframework.common.jdbc.PersistenceExceptionResolver;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventhandling.DomainEventMessage;
import org.axonframework.eventhandling.EventMessage;

import org.axonframework.eventhandling.GenericDomainEventMessage;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.eventsourcing.snapshotting.SnapshotFilter;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.upcasting.event.EventUpcaster;


import java.sql.SQLException;

import javax.sql.DataSource;



/**
 * @author xin
 */
public class CustomJpaEventStorageEngine extends JpaEventStorageEngine {

    public CustomJpaEventStorageEngine(Builder builder) {
        super(builder);
    }

    @Override
    protected Object createEventEntity(EventMessage<?> eventMessage, Serializer serializer) {
        return new CustomDomainEventEntry(asDomainEventMessage(eventMessage), serializer);
    }

    private <T> DomainEventMessage<T> asDomainEventMessage(EventMessage<T> event) {
        return event instanceof DomainEventMessage<?>
                ? (DomainEventMessage<T>) event
                : new GenericDomainEventMessage<>(null, event.getIdentifier(), 0L, event, event::getTimestamp);
    }


    public static CustomJpaEventStorageEngine.Builder builder() {
        return new CustomJpaEventStorageEngine.Builder();
    }

    public static class Builder extends JpaEventStorageEngine.Builder {
        @Override
        public JpaEventStorageEngine.Builder snapshotSerializer(Serializer snapshotSerializer) {
            super.snapshotSerializer(snapshotSerializer);
            return this;
        }

        @Override
        public JpaEventStorageEngine.Builder upcasterChain(EventUpcaster upcasterChain) {
            super.upcasterChain(upcasterChain);
            return this;
        }

        @Override
        public JpaEventStorageEngine.Builder persistenceExceptionResolver(PersistenceExceptionResolver persistenceExceptionResolver) {
            super.persistenceExceptionResolver(persistenceExceptionResolver);
            return this;
        }

        @Override
        public JpaEventStorageEngine.Builder eventSerializer(Serializer eventSerializer) {
            super.eventSerializer(eventSerializer);
            return this;
        }


        @Override
        public JpaEventStorageEngine.Builder snapshotFilter(SnapshotFilter snapshotFilter) {
            super.snapshotFilter(snapshotFilter);
            return this;
        }

        @Override
        public JpaEventStorageEngine.Builder batchSize(int batchSize) {
            super.batchSize(batchSize);
            return this;
        }


        @Override
        public JpaEventStorageEngine.Builder dataSource(DataSource dataSource) throws SQLException {
            super.dataSource(dataSource);
            return this;
        }

        @Override
        public JpaEventStorageEngine.Builder entityManagerProvider(EntityManagerProvider entityManagerProvider) {
            super.entityManagerProvider(entityManagerProvider);
            return this;
        }


        @Override
        public JpaEventStorageEngine.Builder transactionManager(TransactionManager transactionManager) {
            super.transactionManager(transactionManager);
            return this;
        }


        @Override
        public JpaEventStorageEngine.Builder explicitFlush(boolean explicitFlush) {
            super.explicitFlush(explicitFlush);
            return this;
        }


        @Override
        public JpaEventStorageEngine.Builder maxGapOffset(int maxGapOffset) {
            super.maxGapOffset(maxGapOffset);
            return this;
        }


        @Override
        public JpaEventStorageEngine.Builder lowestGlobalSequence(long lowestGlobalSequence) {
            super.lowestGlobalSequence(lowestGlobalSequence);
            return this;
        }


        @Override
        public JpaEventStorageEngine.Builder gapTimeout(int gapTimeout) {
            super.gapTimeout(gapTimeout);
            return this;
        }


        @Override
        public JpaEventStorageEngine.Builder gapCleaningThreshold(int gapCleaningThreshold) {
            super.gapCleaningThreshold(gapCleaningThreshold);
            return this;
        }


        @Override
        public CustomJpaEventStorageEngine build() {
            return new CustomJpaEventStorageEngine(this);
        }


        @Override
        protected void validate() throws AxonConfigurationException {
            super.validate();
        }
    }
}