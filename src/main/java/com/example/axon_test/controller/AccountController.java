package com.example.axon_test.controller;

import com.example.axon_test.command.aggregates.AccountAggregate;
import com.example.axon_test.command.commands.CreateAccountCommand;
import com.example.axon_test.command.commands.QueryAccountCommand;
import com.example.axon_test.command.commands.UpdateAccountCommand;
import com.example.axon_test.config.CustomCommandGateway;
import com.example.axon_test.es.meta.MetaDataInterface;
import com.example.axon_test.es.meta.MetaDataUser;
import com.example.axon_test.es.uuid.UIDGenerator;
import com.example.axon_test.stream.channel.ChannelDefinition;
import lombok.AllArgsConstructor;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

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
        map.put("meta", MetaDataUser.builder().channel(ChannelDefinition.ACCOUNT_INPUT).name("Test").userId(1L).build());
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