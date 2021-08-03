package com.example.axon_test.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author xin
 */
@Entity
@Table(indexes = @Index(columnList = "aggregateIdentifier,type", unique = true))
@Getter
@Setter
@NoArgsConstructor
public class DomainAggregateSequence {

    @Id
    @GeneratedValue
    private Long id;

    private Long sequenceNumber;

    private Long aggregateIdentifier;

    private String type;
}
