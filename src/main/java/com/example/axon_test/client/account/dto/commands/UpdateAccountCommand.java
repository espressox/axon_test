package com.example.axon_test.client.account.dto.commands;


import com.example.axon_test.client.AbstractCommand;
import com.example.axon_test.es.meta.MetaDataInterface;
import lombok.*;

import java.util.HashMap;


/**
 * @author xin
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class UpdateAccountCommand extends AbstractCommand {

    private String accountName;

    public UpdateAccountCommand(Long identifier, String accountNamee, HashMap<String, MetaDataInterface> metaData) {
        super(identifier, false,  metaData);
        this.accountName = accountName;
    }

}
