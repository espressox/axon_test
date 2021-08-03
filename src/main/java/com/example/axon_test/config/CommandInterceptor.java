package com.example.axon_test.config;

import com.example.axon_test.command.commands.AbstractCommand;
import com.example.axon_test.command.commands.CreateAccountCommand;
import com.example.axon_test.common.meta.MetaDataUser;
import com.example.axon_test.common.meta.MetaDataUserInterface;
import com.example.axon_test.helper.UIDGenerator;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * @author xin
 */
@AllArgsConstructor
@Configuration
public class CommandInterceptor implements MessageDispatchInterceptor {

    private final UIDGenerator uidGenerator;

    @Override
    public BiFunction<Integer, GenericCommandMessage<AbstractCommand>, GenericCommandMessage<AbstractCommand>> handle(List messages) {
        return (index, message) -> {

            // create command 自动生成 ID
            if (message.getPayload() instanceof CreateAccountCommand) {
                CreateAccountCommand payload = (CreateAccountCommand) message.getPayload();
                payload.setIdentifier(uidGenerator.getId());
            }

            // 添加 user info 作为 MetaData， 这里就简单的附加一个假的用户
            Map<String, MetaDataUserInterface> map = new HashMap<>(10);

            map.put("user", MetaDataUser.builder().customerId(1L).name("Test").userId(2L).build());
            return map.isEmpty() ? message : message.andMetaData(map);
        };
    }
}