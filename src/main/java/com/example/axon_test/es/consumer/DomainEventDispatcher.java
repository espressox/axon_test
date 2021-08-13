package com.example.axon_test.es.consumer;

import com.example.axon_test.es.event.DomainEvent;
import com.example.axon_test.es.meta.MetaDataInterface;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.function.Consumer;

/**
 * @author xin
 */
@Slf4j
@Component
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
public class DomainEventDispatcher {

    private final StreamDomainEventDispatcher streamDomainEventDispatcher;
    private final Log logger = LogFactory.getLog(getClass());

    @Bean
    public Consumer<DomainEvent<HashMap, HashMap>> receive1() {
        return data -> streamDomainEventDispatcher.dispatchEvent(data,
                ((HashMap<String, String>)data.getMetaData().get("meta")).get("channel"));
    }
}