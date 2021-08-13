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
public class MoneyWithdrawEvent extends AbstractEvent {
    private BigDecimal amount;

    public MoneyWithdrawEvent(Long identifier, BigDecimal amount) {
        super(identifier);
        this.amount = amount;
    }
}
