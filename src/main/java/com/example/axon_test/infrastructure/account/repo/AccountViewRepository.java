package com.example.axon_test.infrastructure.account.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xin
 */
@Repository
public interface AccountViewRepository extends JpaRepository<AccountView, Long> {

}
