package com.example.axon_test.es.producer;

import com.example.axon_test.es.config.CustomDomainEventEntry;
import com.example.axon_test.es.event.DomainEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.function.Supplier;

/**
 * @author xin
 */
@Component
@AllArgsConstructor
@Slf4j
public class DomainEventPublisher {

    private final Sinks.Many<Message<?>> processor = Sinks.many().multicast().onBackpressureBuffer();

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

        String eventType = StringUtils.substringAfterLast(event.getPayloadType(), ".");
        String destinationName = "axon-d-1";
        Message<?> message = MessageBuilder.withPayload(event)
                .setHeader("eventType", eventType)
                .setHeader("spring.cloud.stream.sendto.destination", destinationName).build();

        Sinks.EmitResult em = this.getProcessor().tryEmitNext(message);
    }

    public Flux<Message<?>> getProcessorFlux() {
        return processor.asFlux();
    }
    public Sinks.Many<Message<?>> getProcessor() {
        return processor;
    }

    @Bean
    public Supplier<Flux<Message<?>>> supplier() {
        return this::getProcessorFlux;
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

        DomainEvent<HashMap, HashMap> domainEvent = new DomainEvent<>(
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