package com.example.axon_test.client;

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
public class AbstractEvent {
    @TargetAggregateIdentifier
    private Long identifier;
}
