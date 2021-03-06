package com.example.axon_test.app.account.aggregate;

import com.example.axon_test.client.account.dto.command.CreateAccountCommand;
import com.example.axon_test.client.account.dto.command.UpdateAccountCommand;
import com.example.axon_test.client.account.dto.command.WithdrawMoneyCommand;
import com.example.axon_test.client.account.dto.event.AccountCreatedEvent;
import com.example.axon_test.client.account.dto.event.AccountUpdatedEvent;
import com.example.axon_test.client.account.dto.event.MoneyWithdrawEvent;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.MetaData;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;


/**
 * @author xin
 */
@Getter
@Setter
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Aggregate(repository = "accountAggregateRepository")
public class AccountAggregate {
    @AggregateIdentifier
    private Long identifier;
    private String accountName;
    private BigDecimal balance;

    /**
     * 这里CommandHandler 的注解写在了构造方法上，那么在处理这个 Command 的时候，将会自动创建一个对象。
     * 另外这里的 MetaData 是在 command 发送的时候顺带的附加信息，可以是用户信息，机器信息等等自定义信息。
     * @param command CreateAccountCommand
     * @param metaData MetaData
     */
    @CommandHandler
    public AccountAggregate(CreateAccountCommand command, MetaData metaData) {
        log.info("命令处理程序 " + command);
        if (command.getAmount().compareTo(BigDecimal.ZERO)<0) {
            throw new IllegalArgumentException("amount <= 0");
        }

        AggregateLifecycle.apply(new AccountCreatedEvent(command.getIdentifier(), command.getAccountName(), command.getAmount()), metaData);
    }

    @CommandHandler
    private void handle(UpdateAccountCommand command, MetaData metaData) {
        log.info("命令处理程序 " + command);
        AggregateLifecycle.apply(new AccountUpdatedEvent(command.getIdentifier(), command.getAccountName()), metaData);
    }

    @CommandHandler
    public void handle(WithdrawMoneyCommand command, MetaData metaData){
        log.info("命令处理程序 " + command);
        AggregateLifecycle.apply(new MoneyWithdrawEvent(command.getIdentifier(), command.getAmount()), metaData);
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event){
        log.info("事件溯源处理程序 " + event);
        this.identifier = event.getIdentifier();
        this.accountName = event.getAccountName();
        this.balance = event.getAmount();
        log.info("Account {}-{} is created with balance {}", identifier, accountName, balance);
    }

    @EventSourcingHandler
    public void on(AccountUpdatedEvent event){
        log.info("事件溯源处理程序 " + event);
        this.identifier = event.getIdentifier();
        this.accountName = event.getAccountName();
        log.info("Account {}-{} is created with balance {}", identifier, accountName, balance);
    }

    @EventSourcingHandler
    public void on(MoneyWithdrawEvent event){
        log.info("事件溯源处理程序 " + event);
        BigDecimal result = balance.subtract(event.getAmount());
        if (result.compareTo(BigDecimal.ZERO)<0) {
            log.error("Cannot withdraw more money than the balance!");
            throw new IllegalArgumentException("Cannot withdraw more money than the balance!");
        } else {
            balance = result;
            log.info("Withdraw {} from account {}, balance result: {}", event.getAmount(), identifier, balance);
        }
    }
}
