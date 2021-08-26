package com.example.axon_test.infrastructure.customer.gatewayimpl;

import com.example.axon_test.domain.customer.Customer;
import com.example.axon_test.domain.customer.gateway.CustomerGateway;
import com.example.axon_test.infrastructure.customer.repo.CustomerDO;
import com.example.axon_test.infrastructure.customer.repo.CustomerDORepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author xin
 */
@Component
@AllArgsConstructor
public class CustomerGatewayImpl implements CustomerGateway {

    private final CustomerDORepository customerDORepository;

    @Override
    public Customer getByById(String customerId){
        CustomerDO customerDO = customerDORepository.getById(Long.parseLong(customerId));
        //TODO 在此build Customer
        return null;
    }
}