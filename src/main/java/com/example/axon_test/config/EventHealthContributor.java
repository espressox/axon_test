package com.example.axon_test.config;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;

@Component
@AllArgsConstructor
public class EventHealthContributor implements InfoContributor {

    private final CustomDomainEventEntryRepository customDomainEventEntryRepository;

    @Override
    public void contribute(Info.Builder builder) {
        Long count = customDomainEventEntryRepository.countBySentFalse();

        builder.withDetail("failedMessage", count);
    }
}