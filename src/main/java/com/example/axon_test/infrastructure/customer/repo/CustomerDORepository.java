package com.example.axon_test.infrastructure.customer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xin
 */
@Repository
public interface CustomerDORepository extends JpaRepository<CustomerDO, Long> {
}
