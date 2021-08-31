package com.example.axon_test.app.config;

import com.example.axon_test.app.account.aggregate.AccountAggregate;
import com.example.axon_test.es.config.CustomEmbeddedEventStore;
import com.example.axon_test.es.config.CustomEventSourcingRepository;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.messaging.annotation.ParameterResolverFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xin
 */
@Configuration
public class AppConfig {
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

}
