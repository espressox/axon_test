package com.example.axon_test.command.events;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author xin
 */

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class AccountCreatedEvent extends AbstractEvent  {
    private String accountName;
    private BigDecimal amount;

    public AccountCreatedEvent(Long identifier, String accountName, BigDecimal amount) {
        super(identifier);
        this.accountName = accountName;
        this.amount = amount;
    }
}
