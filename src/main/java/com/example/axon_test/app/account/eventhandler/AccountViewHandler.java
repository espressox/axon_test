package com.example.axon_test.app.account.eventhandler;

import com.example.axon_test.client.DomainEventTopic;
import com.example.axon_test.client.account.dto.event.AccountCreatedEvent;
import com.example.axon_test.es.event.DomainEvent;
import com.example.axon_test.es.consumer.StreamEventHandler;
import com.example.axon_test.infrastructure.account.service.AccountViewService;
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

    @StreamEventHandler(types = DomainEventTopic.ACCOUNT_TOPIC)
    public void handle(AccountCreatedEvent event, DomainEvent<HashMap, HashMap> domainEvent) {
        accountViewService.updateViewFromAggregateById(event.getIdentifier(), domainEvent.getTimestamp());
    }
}
