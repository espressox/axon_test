package com.example.axon_test.handler;

import com.example.axon_test.command.aggregates.AccountAggregate;
import com.example.axon_test.command.events.AbstractEvent;
import com.example.axon_test.config.CustomEventSourcingRepository;
import com.example.axon_test.entity.AccountView;
import com.example.axon_test.entity.AccountViewRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.DomainEventMessage;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcedAggregate;
import org.axonframework.modelling.command.LockAwareAggregate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;

/**
 * @author xin
 */
@Component
@Slf4j
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AccountViewHandler {

    private final CustomEventSourcingRepository<AccountAggregate> eventSourcingRepository;

    private final AccountViewRepository accountViewRepository;

    /**
     * 任何 BankAccount 事件发生之后，重新计算 aggregate 的最新状态，转换成 view 之后存储到本地
     *
     * @param event   any event from BankAccount
     * @param message domain event wrapper
     */
    @EventHandler
    public void on(AbstractEvent event, DomainEventMessage<AbstractEvent> message) {
        log.info(MessageFormat.format("{0}: {1} , seq: {2}, payload: {3}", message.getType(), message.getAggregateIdentifier(), message.getSequenceNumber(), message.getPayload()));

        updateBankAccountView(message.getAggregateIdentifier());
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateBankAccountView(String id) {
        LockAwareAggregate<AccountAggregate, EventSourcedAggregate<AccountAggregate>> lockAwareAggregate = eventSourcingRepository.load(id);
        AccountAggregate aggregate = lockAwareAggregate.getWrappedAggregate().getAggregateRoot();


        AccountView accountView = accountViewRepository.findById(Long.valueOf(id)).orElse(new AccountView());
        accountView.setId(aggregate.getIdentifier());
        accountView.setAccountName(aggregate.getAccountName());
        accountView.setBalance(aggregate.getBalance());

        accountViewRepository.save(accountView);
    }


}
