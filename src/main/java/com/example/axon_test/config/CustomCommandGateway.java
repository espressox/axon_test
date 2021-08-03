package com.example.axon_test.config;

import com.example.axon_test.command.aggregates.AccountAggregate;
import com.example.axon_test.command.commands.AbstractCommand;
import org.axonframework.commandhandling.gateway.Timeout;
import org.axonframework.messaging.annotation.MetaDataValue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author xin
 */
public interface CustomCommandGateway {
    /**
     * fire and forget
     * @param command AbstractCommand
     */
    void sendCommand(AbstractCommand command);

    /**
     *
     * method that will wait for a result for 10 seconds
     * @param command AbstractCommand
     * @return Long
     */
    @Timeout(value = 6, unit = TimeUnit.SECONDS)
    Long sendCommandAndWaitForAResult(AbstractCommand command);


    /**
     *
     * method that will wait for a result for 10 seconds
     * @param command AbstractCommand
     */
    @Timeout(value = 6, unit = TimeUnit.SECONDS)
    void sendCommandAndWait(AbstractCommand command);

    /**
     * method that attaches meta data and will wait for a result for 10 seconds
     * @param command AbstractCommand
     * @param userId String
     */
    @Timeout(value = 6, unit = TimeUnit.SECONDS)
    AccountAggregate sendCommandAndWaitForAResult(AbstractCommand command,
                                                  @MetaDataValue("userId") String userId);

    /**
     * this method will also wait, caller decides how long
     * @param command AbstractCommand
     * @param timeout long
     * @param unit TimeUnit
     * @throws TimeoutException timeout
     * @throws InterruptedException interrupted
     */
    void sendCommandAndWait(AbstractCommand command, long timeout, TimeUnit unit) throws TimeoutException, InterruptedException;
}
