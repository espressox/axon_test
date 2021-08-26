package com.example.axon_test.es.config;

import com.example.axon_test.client.AbstractCommand;
import com.example.axon_test.es.uuid.UIDGenerator;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.context.annotation.Configuration;

import java.util.List;
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
//            if (message.getPayload() instanceof CreateAccountCommand) {
//                CreateAccountCommand payload = (CreateAccountCommand) message.getPayload();
//                payload.setIdentifier(uidGenerator.getId());
//            }
            if (message.getPayload().isCreate()) {
                message.getPayload().setIdentifier(uidGenerator.getId());
            }

            // 添加 MetaData
            return message.getPayload().getMetaData().isEmpty() ? message : message.andMetaData(message.getPayload().getMetaData());
        };
    }
}