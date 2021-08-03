package com.example.axon_test.config;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xin
 */
@Repository
public interface CustomDomainEventEntryRepository extends JpaRepository<CustomDomainEventEntry, String> {

    /**
     * 查找事件
     *
     * @param  identifier String
     * @return CustomDomainEventEntry
     */
    CustomDomainEventEntry findByEventIdentifier(String identifier);

    /**
     * 查找未发送的事件
     *
     * @param pageable Pageable
     *
     * @return Page<CustomDomainEventEntry>
     */
    Page<CustomDomainEventEntry> findBySentFalse(Pageable pageable);

    /**
     * 查询未发送事件的数量
     * @return Long
     */
    Long countBySentFalse();
}