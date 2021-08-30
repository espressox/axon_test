package com.example.axon_test.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author xin
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ApiToModelDef {
    /**
     * customer api
     */
    CUSTOMER_MODEL("journal.customer", "com.example.axon_test.domain.customer.Customer");

    private String api;
    private String apiTo;


    public static String getByApi(String api) {
        for (ApiToModelDef amd: ApiToModelDef.values()) {
            if (amd.getApi().equalsIgnoreCase(api)) {
                return amd.getApiTo();
            }
        }
        return null;
    }

}
