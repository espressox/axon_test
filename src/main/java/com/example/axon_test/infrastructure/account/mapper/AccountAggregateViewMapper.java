package com.example.axon_test.infrastructure.account.mapper;


import com.example.axon_test.app.account.aggregate.AccountAggregate;
import com.example.axon_test.infrastructure.account.repo.AccountView;

/**
 * @author xin
 */
public class AccountAggregateViewMapper {
    public static void mapAggregateToView(AccountAggregate aggregate, AccountView accountView) {
        accountView.setId(aggregate.getIdentifier());
        accountView.setAccountName(aggregate.getAccountName());
        accountView.setBalance(aggregate.getBalance());
    }
}
