package com.example.axon_test.handler;

import com.example.axon_test.command.aggregates.AccountAggregate;
import com.example.axon_test.command.commands.QueryAccountCommand;
import com.example.axon_test.command.events.AbstractEvent;
import com.example.axon_test.command.events.AccountCreatedEvent;
import com.example.axon_test.common.event.DomainEvent;
import com.example.axon_test.config.CustomEventSourcingRepository;
import com.example.axon_test.config.StreamEventHandler;
import com.example.axon_test.entity.AccountView;
import com.example.axon_test.entity.AccountViewRepository;
import com.example.axon_test.service.AccountViewService;
import com.example.axon_test.stream.channel.ChannelDefinition;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.DomainEventMessage;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcedAggregate;
import org.axonframework.modelling.command.LockAwareAggregate;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.HashMap;

/**
 * @author xin
 */
@Component
@Slf4j
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AccountViewHandlerNew {

    private final AccountViewService accountViewService;

    @StreamEventHandler(types = ChannelDefinition.ACCOUNT_INPUT)
    public void handle(AccountCreatedEvent event, DomainEvent<AccountCreatedEvent, HashMap> domainEvent) {
        accountViewService.updateViewFromAggregateById(event.getIdentifier(), domainEvent.getTimestamp());
    }
}
