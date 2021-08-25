package com.example.axon_test.command.events;

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
public class RevaluationEvent extends AbstractEvent  {
    private String bizContent;

    public RevaluationEvent(Long identifier, String bizContent) {
        super(identifier);
        this.bizContent = bizContent;
    }
}
