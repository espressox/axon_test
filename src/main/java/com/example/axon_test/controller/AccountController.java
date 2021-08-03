package com.example.axon_test.controller;

import com.example.axon_test.command.aggregates.AccountAggregate;
import com.example.axon_test.command.commands.CreateAccountCommand;
import com.example.axon_test.command.commands.QueryAccountCommand;
import com.example.axon_test.command.commands.UpdateAccountCommand;
import com.example.axon_test.config.CustomCommandGateway;
import com.example.axon_test.helper.UIDGenerator;
import lombok.AllArgsConstructor;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;

/**
 * @author xin
 */
@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private final CustomCommandGateway commandGateway;
    private final QueryGateway queryGateway;
    private final UIDGenerator uidGenerator;

    @PostMapping
    public void createAccount(@RequestBody @Valid CreateAccountCommand command) {
        command.setIdentifier(uidGenerator.getId());
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