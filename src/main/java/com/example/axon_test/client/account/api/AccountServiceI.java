package com.example.axon_test.client.account.api;


import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.example.axon_test.ds.bean.in.ServiceInOriRequest;
import com.example.axon_test.ds.bean.in.ServiceInOriResponse;
import com.example.axon_test.es.event.DomainEvent;

import java.util.HashMap;

/**
 * @author xin
 */
public interface AccountServiceI {
    /**
     * add customer service
     * @param serviceInOriRequest ServiceInOriRequest
     * @return Response
     */
    public <T> MultiResponse<T> accountSvc(ServiceInOriRequest serviceInOriRequest);


    /**
     * event service
     * @param domainEvent DomainEvent
     */
    public void accountSvc(DomainEvent<HashMap<Object, Object>, HashMap<Object, Object>> domainEvent);
}
