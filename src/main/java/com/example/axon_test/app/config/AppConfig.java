package com.example.axon_test.app.config;

import com.example.axon_test.app.account.aggregate.AccountAggregate;
import com.example.axon_test.domain.ApiToExecutorDef;
import com.example.axon_test.domain.ApiToModelDef;
import com.example.axon_test.ds.factory.ServiceAPIDefFactory;
import com.example.axon_test.es.config.CustomEmbeddedEventStore;
import com.example.axon_test.es.config.CustomEventSourcingRepository;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.messaging.annotation.ParameterResolverFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

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

    @Bean
    public ServiceAPIDefFactory setApiDef() {
        ServiceAPIDefFactory serviceAPIDefFactory = new ServiceAPIDefFactory();
        Map<String, String> modelApiMap = new HashMap<>();
        Map<String, String> exeApiMap = new HashMap<>();
            for (ApiToModelDef amd: ApiToModelDef.values()) {
               modelApiMap.put(amd.getApi(), amd.getApiTo()) ;
            }
        serviceAPIDefFactory.setModelMap(modelApiMap);
        for (ApiToExecutorDef aed: ApiToExecutorDef.values()) {
            exeApiMap.put(aed.getApi(), aed.getApiTo()) ;
        }
        serviceAPIDefFactory.setExeMap(exeApiMap);

        return serviceAPIDefFactory;
    }
}
