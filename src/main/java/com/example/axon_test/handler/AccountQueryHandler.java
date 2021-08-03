package com.example.axon_test.handler;

import com.example.axon_test.command.aggregates.AccountAggregate;
import com.example.axon_test.command.commands.QueryAccountCommand;
import com.example.axon_test.config.CustomEventSourcingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventsourcing.EventSourcedAggregate;
import org.axonframework.modelling.command.LockAwareAggregate;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

/**
 * @author xin
 */
@Component
@AllArgsConstructor
@Slf4j
public class AccountQueryHandler {
    private final CustomEventSourcingRepository<AccountAggregate> eventSourcingRepository;

    @QueryHandler
    public AccountAggregate on(QueryAccountCommand command) {
        LockAwareAggregate<AccountAggregate, EventSourcedAggregate<AccountAggregate>> lockAwareAggregate = eventSourcingRepository.load(command.getId().toString(), command.getEndDate());
        return lockAwareAggregate.getWrappedAggregate().getAggregateRoot();
    }
}
