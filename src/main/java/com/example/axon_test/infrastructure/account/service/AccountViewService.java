package com.example.axon_test.infrastructure.account.service;

import com.example.axon_test.app.account.aggregate.AccountAggregate;
import com.example.axon_test.client.account.dto.commands.QueryAccountCommand;
import com.example.axon_test.infrastructure.account.repo.AccountView;
import com.example.axon_test.infrastructure.account.repo.AccountViewRepository;
import com.example.axon_test.infrastructure.account.mapper.AccountAggregateViewMapper;
import lombok.AllArgsConstructor;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * @author xin
 */
@Service
@AllArgsConstructor
public class AccountViewService {

    private final QueryGateway queryGateway;
    private final AccountViewRepository accountViewRepository;

    public void updateViewFromAggregateById(Long aggregateIdentifier, Instant time) {

        QueryAccountCommand command = new QueryAccountCommand(aggregateIdentifier, time);
        AccountAggregate aggregate = queryGateway.query(command, AccountAggregate.class).join();
        AccountView view = accountViewRepository.findById(aggregateIdentifier).orElse(new AccountView());

        AccountAggregateViewMapper.mapAggregateToView(aggregate, view);
        accountViewRepository.save(view);
    }
}