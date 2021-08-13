package com.example.axon_test.es.uuid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xin
 */
@Repository
public interface WorkerIdRepository extends JpaRepository<WorkerId, Long> {
    /**
     * find
     * @param  serviceKey String
     * @return WorkerId
     */
    WorkerId findByServiceKey(String serviceKey);
}