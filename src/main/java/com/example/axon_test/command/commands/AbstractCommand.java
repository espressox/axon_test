package com.example.axon_test.command.commands;

import lombok.*;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

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
}
