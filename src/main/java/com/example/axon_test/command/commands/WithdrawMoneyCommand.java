package com.example.axon_test.command.commands;


import com.example.axon_test.es.meta.MetaDataInterface;
import com.example.axon_test.es.meta.MetaDataUser;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashMap;


/**
 * @author xin
 */

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class WithdrawMoneyCommand extends AbstractCommand {

    private BigDecimal amount;

    public WithdrawMoneyCommand(Long identifier, BigDecimal amountn, HashMap<String, MetaDataInterface> metaData) {
        super(identifier, false, metaData);
        this.amount = amount;
    }
}
