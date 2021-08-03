package com.example.axon_test.config;

import com.example.axon_test.common.event.DomainEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.HashMap;

/**
 * @author xin
 */
@Component
@AllArgsConstructor
@Slf4j
public class EventPublisher {

//    private final OutputChannel outputChannel;

    public void sendEvent(DomainEvent event) {

        // use stream to send message here
        log.info(MessageFormat.format("prepare to send message : {0}]", new Gson().toJson(event)));

//        // 为了兼容 SCS 原生的 header 路由规则，这里在 header 中写入 eventType
//        String eventType = StringUtils.substringAfterLast(event.getPayloadType(), ".");
//        MessageBuilder<DomainEvent> messageBuilder = MessageBuilder.withPayload(event);
//        if (null != eventType) {
//            messageBuilder.setHeader("eventType", eventType);
//            messageBuilder.setHeader("messageType", "eventSourcing");
//        }
//        outputChannel.contract().send(messageBuilder.build());
    }

    public void sendEvent(CustomDomainEventEntry event) {
        // use stream to send message here
        ObjectMapper mapper = new ObjectMapper();

        HashMap payload = null;
        HashMap metaData = null;
        try {
            payload = mapper.readValue(event.getPayload().getData(), HashMap.class);
            metaData = mapper.readValue(event.getMetaData().getData(), HashMap.class);
        } catch (Exception exception) {
            log.error(MessageFormat.format("byte[] to string failed; exception: {0}", exception));
        }

        if (payload == null || metaData == null) {
            log.warn(MessageFormat.format("nothing to send; exception: {0}", event.getEventIdentifier()));
            return;
        }

        DomainEvent domainEvent = new DomainEvent(
                event.getType(),
                event.getAggregateIdentifier(),
                event.getPayload().getType().getName(),
                event.getPayload().getType().getRevision(),
                event.getSequenceNumber(),
                event.getEventIdentifier(),
                event.getTimestamp(),
                payload,
                metaData);

        this.sendEvent(domainEvent);
    }

}