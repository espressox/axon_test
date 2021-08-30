package com.example.axon_test.client.account.dto.event;

import com.example.axon_test.client.AbstractEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author xin
 */

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
public class AccountUpdatedEvent extends AbstractEvent {
    private String accountName;

    public AccountUpdatedEvent(Long identifier, String accountName) {
        super(identifier);
        this.accountName = accountName;
    }
}
