package com.example.axon_test.command.commands;

import com.example.axon_test.es.meta.MetaDataInterface;
import com.example.axon_test.es.meta.MetaDataUser;
import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.HashMap;

/**
 * @author xin
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AbstractCommand {
    @TargetAggregateIdentifier
    private Long identifier;

    private boolean isCreate;

    private HashMap<String, MetaDataInterface> metaData;

}
