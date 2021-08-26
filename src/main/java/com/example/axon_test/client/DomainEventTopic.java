package com.example.axon_test.client;

/**
 * @author xin
 */
public class DomainEventTopic {

    public static final String CUSTOMER_TOPIC = "CRM_CUSTOMER_TOPIC";

    public static final String ACCOUNT_TOPIC = "ACT_ACCOUNT_TOPIC";

    private DomainEventTopic() {
        throw new IllegalStateException("Utility class");
    }

}
