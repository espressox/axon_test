package com.example.axon_test.config;

import com.example.axon_test.es.producer.DomainEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.PostPersist;
import java.util.concurrent.CompletableFuture;


/**
 * @author xin
 */
@Component
@Slf4j
public class CustomDomainEventEntryListener {
    private static CustomDomainEventEntryRepository customDomainEventEntryRepository;

    private static DomainEventPublisher domainEventPublisher;

    @Autowired
    public void init(CustomDomainEventEntryRepository customDomainEventEntryRepository, DomainEventPublisher domainEventPublisher) {
        this.customDomainEventEntryRepository = customDomainEventEntryRepository;
        this.domainEventPublisher = domainEventPublisher;
    }

    @PostPersist
    void onPersist(CustomDomainEventEntry entry) {

        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {

            @Override
            public void afterCompletion(int status) {
                if (status == TransactionSynchronization.STATUS_COMMITTED) {
                    CompletableFuture.runAsync(() -> sendEvent(entry.getEventIdentifier()));
                }
            }
        });
    }

    @Transactional(rollbackFor = Exception.class)
    public void sendEvent(String identifier) {
        CustomDomainEventEntry eventEntry = customDomainEventEntryRepository.findByEventIdentifier(identifier);

        if (!eventEntry.isSent()) {
            domainEventPublisher.sendEvent(eventEntry);
            eventEntry.setSent(true);
            customDomainEventEntryRepository.save(eventEntry);
        }
    }
}