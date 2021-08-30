package com.example.axon_test.app.customer;

import com.alibaba.cola.dto.MultiResponse;
import com.example.axon_test.client.customer.api.CustomerServiceI;
import com.example.axon_test.ds.bean.in.InProcessContext;
import com.example.axon_test.ds.bean.in.ServiceInOriRequest;
import com.example.axon_test.ds.bean.in.ServiceInOriResponse;
import com.example.axon_test.ds.factory.ServiceInOriReqFactory;
import com.example.axon_test.ds.process.in.InServiceEngine;
import com.example.axon_test.es.event.DomainEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author xin
 */
@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerServiceI {

    private final InServiceEngine inServiceEngine;


    @Override
    public <T> MultiResponse<T> customerSvc(String reqJson) throws JsonProcessingException {
        InProcessContext inProcessContext = new InProcessContext();
        ServiceInOriRequest serviceInOriRequest = ServiceInOriReqFactory.buildRequest(reqJson);
        inProcessContext.build(serviceInOriRequest);
        inServiceEngine.serviceProcess(inProcessContext);
        ServiceInOriResponse serviceInOriResponse = inProcessContext.getServiceInOriResponse();
        return null;
    }

    @Override
    public void customerSvc(DomainEvent<HashMap<Object, Object>, HashMap<Object, Object>> domainEvent) {

    }
}
