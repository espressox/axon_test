package com.example.axon_test.command.commands;


import com.example.axon_test.common.domain.AccountId;
import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;


/**
 * @author xin
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class UpdateAccountCommand extends AbstractCommand implements AccountId {

    private String accountName;

    public UpdateAccountCommand(Long identifier, String accountNamee) {
        super(identifier);
        this.accountName = accountName;
    }

}
