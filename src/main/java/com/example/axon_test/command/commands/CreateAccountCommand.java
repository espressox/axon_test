package com.example.axon_test.command.commands;


import com.example.axon_test.common.domain.AccountId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;


/**
 * @author xin
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class CreateAccountCommand extends AbstractCommand implements AccountId {

    private String accountName;

    private BigDecimal amount;

    public CreateAccountCommand(Long identifier, String accountName, BigDecimal amount) {
        super(identifier);
        this.amount = amount;
        this.accountName = accountName;
    }

}
