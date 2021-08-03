package com.example.axon_test.mapper;


import com.example.axon_test.command.aggregates.AccountAggregate;
import com.example.axon_test.entity.AccountView;

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
