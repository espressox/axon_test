package com.example.axon_test.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author xin
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ApiToExecutorDef {
    /**
     * customer api
     */
    CUSTOMER_EXECUTOR("journal.customer", "customerServiceExecutor");

    private String api;
    private String apiTo;


    public static String getByApi(String api) {
        for (ApiToExecutorDef amd: ApiToExecutorDef.values()) {
            if (amd.getApi().equalsIgnoreCase(api)) {
                return amd.getApiTo();
            }
        }
        return null;
    }

}
