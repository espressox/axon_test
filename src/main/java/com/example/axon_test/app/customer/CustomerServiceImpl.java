package com.example.axon_test.app.customer;

import com.alibaba.cola.dto.Response;
import com.example.axon_test.client.customer.api.CustomerServiceI;
import com.example.axon_test.ds.bean.process.Instruction;
import com.example.axon_test.ds.process.in.InServiceEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xin
 */
@Component
public class CustomerServiceImpl implements CustomerServiceI {

    @Autowired
    InServiceEngine inServiceEngine;


    @Override
    public Response addCustomer(Instruction instruction) {
        inServiceEngine.serviceProcess(instruction);
        return null;
    }
}
