package com.example.axon_test.command.commands;


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
public class WithdrawMoneyCommand extends AbstractCommand implements AccountId  {

    private BigDecimal amount;

    public WithdrawMoneyCommand(Long identifier, BigDecimal amount) {
        super(identifier);
        this.amount = amount;
    }
}
