package com.example.axon_test.domain.customer.gateway;

import com.example.axon_test.domain.customer.Customer;

/**
 * @author xin
 */
public interface CustomerGateway {
    /**
     * get customer by customer's identity
     * @param customerId String
     * @return Customer
     */
    Customer getByById(String customerId);
}
