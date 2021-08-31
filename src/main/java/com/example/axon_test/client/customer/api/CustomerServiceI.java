package com.example.axon_test.client.customer.api;


import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.example.axon_test.ds.bean.in.ServiceInModelRequest;
import com.example.axon_test.ds.bean.in.ServiceInOriRequest;
import com.example.axon_test.ds.bean.process.Instruction;
import com.example.axon_test.es.event.DomainEvent;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author xin
 */
public interface CustomerServiceI {
    /**
     * add customer service
     * @param reqJson String
     * @return Response
     */
    public <T> MultiResponse<T> customerSvc(String reqJson) throws JsonProcessingException;

    /**
     * event service
     * @param domainEvent DomainEvent
     */
    public void customerSvc(DomainEvent<HashMap<Object, Object>, HashMap<Object, Object>> domainEvent);
}
