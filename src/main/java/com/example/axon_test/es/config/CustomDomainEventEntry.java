package com.example.axon_test.es.config;

import lombok.Getter;
import lombok.Setter;
import org.axonframework.eventhandling.AbstractSequencedDomainEventEntry;
import org.axonframework.eventhandling.DomainEventMessage;
import org.axonframework.serialization.Serializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author xin
 */
@Entity
@Getter
@Setter
@Table(name = "DomainEventEntry", indexes = @Index(columnList = "aggregateIdentifier,sequenceNumber", unique = true))
@EntityListeners(CustomDomainEventEntryListener.class)
public class CustomDomainEventEntry extends AbstractSequencedDomainEventEntry<byte[]> {

    @NotNull
    @Column(columnDefinition = "tinyint(1) default 0")
    private boolean sent = false;

    public CustomDomainEventEntry(DomainEventMessage<?> eventMessage, Serializer serializer) {
        super(eventMessage, serializer, byte[].class);
        this.setSent(false);
    }

    /**
     * Default constructor required by JPA
     */
    protected CustomDomainEventEntry() {
    }
}
