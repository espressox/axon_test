package com.example.axon_test.adapter.web.acount;

import com.example.axon_test.client.DomainEventTopic;
import com.example.axon_test.app.account.aggregate.AccountAggregate;
import com.example.axon_test.client.account.dto.commands.CreateAccountCommand;
import com.example.axon_test.client.account.dto.commands.QueryAccountCommand;
import com.example.axon_test.client.account.dto.commands.UpdateAccountCommand;
import com.example.axon_test.es.config.CustomCommandGateway;
import com.example.axon_test.es.meta.MetaDataInterface;
import com.example.axon_test.es.meta.MetaDataUser;
import lombok.AllArgsConstructor;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.util.HashMap;

/**
 * @author xin
 */
@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private final CustomCommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @PostMapping
    public void createAccount(@RequestBody @Valid CreateAccountCommand command) {
        HashMap<String, MetaDataInterface> map = new HashMap<>(10);
        map.put("meta", MetaDataUser.builder().channel(DomainEventTopic.ACCOUNT_TOPIC).name("Test").userId(1L).build());
        command.setMetaData(map);
        command.setCreate(true);
        commandGateway.sendCommandAndWaitForAResult(command);
    }

    @PutMapping("/{id}")
    public void updateAccount(@PathVariable("id") Long id, @RequestBody @Valid UpdateAccountCommand command) {
        commandGateway.sendCommandAndWait(command);
    }

    @GetMapping("/{id}")
    public AccountAggregate getContract(@PathVariable("id") Long id) {
        QueryAccountCommand command = new QueryAccountCommand(id, Instant.now());

        return queryGateway.query(command, AccountAggregate.class).join();
    }


}