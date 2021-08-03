package com.example.axon_test.command.events;

import com.example.axon_test.common.domain.AccountId;
import lombok.*;

import java.math.BigDecimal;

/**
 * @author xin
 */

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class AccountCreatedEvent extends AbstractEvent implements AccountId {
    private String accountName;
    private BigDecimal amount;

    public AccountCreatedEvent(Long identifier, String accountName, BigDecimal amount) {
        super(identifier);
        this.accountName = accountName;
        this.amount = amount;
    }
}
