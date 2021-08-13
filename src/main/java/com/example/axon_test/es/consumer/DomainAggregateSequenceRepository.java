package com.example.axon_test.es.consumer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xin
 */
@Repository
public interface DomainAggregateSequenceRepository extends JpaRepository<DomainAggregateSequence, Long> {

    /**
     * 根据 aggregate id 和 type 找到对应的记录
     *
     * @param identifier Long
     * @param type String
     * @return DomainAggregateSequence
     */
    DomainAggregateSequence findByAggregateIdentifierAndType(Long identifier, String type);

}