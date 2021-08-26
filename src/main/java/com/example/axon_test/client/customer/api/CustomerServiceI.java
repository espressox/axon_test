package com.example.axon_test.client.customer.api;


import com.alibaba.cola.dto.Response;
import com.example.axon_test.ds.bean.process.Instruction;

/**
 * @author xin
 */
public interface CustomerServiceI {
    /**
     * add customer service
     * @param instruction Instruction
     * @return Response
     */
    public Response addCustomer(Instruction instruction);
}
