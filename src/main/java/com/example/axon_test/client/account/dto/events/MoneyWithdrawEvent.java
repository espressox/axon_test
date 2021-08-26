package com.example.axon_test.client.account.dto.events;

import com.example.axon_test.client.AbstractEvent;
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
