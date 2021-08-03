package com.example.axon_test.command.events;

import com.example.axon_test.common.domain.AccountId;
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
public class AccountUpdatedEvent extends AbstractEvent implements AccountId {
    private String accountName;

    public AccountUpdatedEvent(Long identifier, String accountName) {
        super(identifier);
        this.accountName = accountName;
    }
}
