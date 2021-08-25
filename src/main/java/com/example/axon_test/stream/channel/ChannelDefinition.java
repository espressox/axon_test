package com.example.axon_test.stream.channel;

/**
 * @author xin
 */
public class ChannelDefinition {

    public static final String ACCOUNT = "account-events";

    public static final String ACCOUNT_INPUT = "account-events-input";

    public static final String STOCK_REVALUATION = "stock-revaluation";


    private ChannelDefinition() {
        throw new IllegalStateException("Utility class");
    }
}
