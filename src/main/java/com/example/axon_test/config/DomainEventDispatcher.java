package com.example.axon_test.config;

import com.example.axon_test.common.event.DomainEvent;
import com.example.axon_test.stream.channel.ChannelDefinition;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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


    @Bean
    public Consumer<DomainEvent> eventSourcing() {
        return data -> streamDomainEventDispatcher.dispatchEvent(data, ChannelDefinition.ACCOUNT_INPUT);
    }
}