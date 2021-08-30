package com.example.axon_test.client.customer.dto.event;

import com.example.axon_test.client.AbstractEvent;
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
public class CustomerEvent extends AbstractEvent {
    private String customerId;
    private String memberId;
    private String globalId;
    private Long registeredCapital;
    private String companyName;

    public CustomerEvent(Long identifier, String customerId, String memberId, String globalId, Long registeredCapital, String companyName) {
        super(identifier);
        this.customerId = customerId;
        this.memberId = memberId;
        this.globalId = globalId;
        this.registeredCapital = registeredCapital;
        this.companyName = companyName;
    }
}
