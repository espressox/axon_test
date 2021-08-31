package com.example.axon_test.client.customer.dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private String customerId;
    private String memberId;
    private String globalId;
    private Long registeredCapital;
    private String companyName;
}
