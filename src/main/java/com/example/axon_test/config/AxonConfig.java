package com.example.axon_test.config;

import com.example.axon_test.command.aggregates.AccountAggregate;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.gateway.CommandGatewayFactory;
import org.axonframework.common.jdbc.PersistenceExceptionResolver;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventsourcing.*;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.messaging.annotation.ParameterResolverFactory;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.upcasting.event.EventUpcaster;
import org.axonframework.spring.config.AxonConfiguration;
import org.axonframework.springboot.util.RegisterDefaultEntities;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;


/**
 * @author xin
 */
@Configuration
@RegisterDefaultEntities(packages = {
        "org.axonframework.eventsourcing.eventstore.jpa"
})
public class AxonConfig {

    @Bean
    public CustomCommandGateway getCommandGateway(SimpleCommandBus simpleCommandBus, CommandInterceptor commandInterceptor) {
        return CommandGatewayFactory.builder()
                .commandBus(simpleCommandBus)
                .retryScheduler(new CommandRetryScheduler())
                .dispatchInterceptors(commandInterceptor)
                .build()
                .createGateway(CustomCommandGateway.class);
    }

    @Bean
    public CustomEmbeddedEventStore eventStore(EventStorageEngine storageEngine, AxonConfiguration configuration) {
        return CustomEmbeddedEventStore.builder()
                .storageEngine(storageEngine)
                .messageMonitor(configuration.messageMonitor(EventStore.class, "eventStore"))
                .build();
    }

    @Bean
    public CustomEventSourcingRepository<AccountAggregate> accountAggregateRepository(CustomEmbeddedEventStore eventStore,
                                                                                        SnapshotTriggerDefinition snapshotTriggerDefinition,
                                                                                        ParameterResolverFactory parameterResolverFactory) {
        return CustomEventSourcingRepository.builder(AccountAggregate.class)
                .eventStore(eventStore)
                .snapshotTriggerDefinition(snapshotTriggerDefinition)
                .parameterResolverFactory(parameterResolverFactory)
                .build();
    }

    @Bean
    public SnapshotTriggerDefinition snapshotTriggerDefinition(Snapshotter snapshotter) {
        return new EventCountSnapshotTriggerDefinition(snapshotter, 5);
    }

    @Bean
    public AggregateSnapshotter snapShotter(CustomEmbeddedEventStore eventStore, ParameterResolverFactory parameterResolverFactory) {
        return AggregateSnapshotter.builder()
                .eventStore(eventStore)
                .parameterResolverFactory(parameterResolverFactory)
                .aggregateFactories(Collections.singletonList(new GenericAggregateFactory<>(AccountAggregate.class)))
                .build();
    }

    @Bean
    public EventStorageEngine eventStorageEngine(Serializer defaultSerializer,
                                                 PersistenceExceptionResolver persistenceExceptionResolver,
                                                 @Qualifier("eventSerializer") Serializer eventSerializer,
                                                 EntityManagerProvider entityManagerProvider,
                                                 TransactionManager transactionManager) {
        return CustomJpaEventStorageEngine.builder()
                .snapshotSerializer(defaultSerializer)
                .persistenceExceptionResolver(persistenceExceptionResolver)
                .eventSerializer(eventSerializer)
                .entityManagerProvider(entityManagerProvider)
                .transactionManager(transactionManager)
                .build();
    }

    @Bean
    public EventProcessingConfigurer eventProcessingConfigurer(EventProcessingConfigurer eventProcessingConfigurer) {
        eventProcessingConfigurer.usingSubscribingEventProcessors();
        return eventProcessingConfigurer;
    }
}
