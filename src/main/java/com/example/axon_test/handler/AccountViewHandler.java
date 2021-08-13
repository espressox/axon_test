package com.example.axon_test.handler;

import com.example.axon_test.command.events.AccountCreatedEvent;
import com.example.axon_test.es.event.DomainEvent;
import com.example.axon_test.es.consumer.StreamEventHandler;
import com.example.axon_test.es.meta.MetaDataInterface;
import com.example.axon_test.service.AccountViewService;
import com.example.axon_test.stream.channel.ChannelDefinition;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

/**
 * @author xin
 */
@Component
@Slf4j
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AccountViewHandler {

    private final AccountViewService accountViewService;

    @StreamEventHandler(types = ChannelDefinition.ACCOUNT_INPUT)
    public void handle(AccountCreatedEvent event, DomainEvent<HashMap, HashMap> domainEvent) {
        accountViewService.updateViewFromAggregateById(event.getIdentifier(), domainEvent.getTimestamp());
    }
}
