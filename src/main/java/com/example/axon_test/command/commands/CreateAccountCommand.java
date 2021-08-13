package com.example.axon_test.command.commands;


import com.example.axon_test.es.meta.MetaDataInterface;
import com.example.axon_test.es.meta.MetaDataUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.HashMap;


/**
 * @author xin
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class CreateAccountCommand extends AbstractCommand {

    private String accountName;

    private BigDecimal amount;

    public CreateAccountCommand(Long identifier, Boolean isCreate, String accountName, BigDecimal amount, HashMap<String, MetaDataInterface> metaData) {
        super(identifier, isCreate,  metaData);
        this.amount = amount;
        this.accountName = accountName;
    }

}
