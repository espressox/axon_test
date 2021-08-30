
package com.example.axon_test.ds.factory;


import com.example.axon_test.ds.bean.in.ServiceInOriRequest;
import com.example.axon_test.es.event.DomainEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 原始请求参数工厂
 * 
 * @author XinWei
 * @version $Id: ServiceInOciReqFactory
 */
public class ServiceInOriReqFactory {

    public static ServiceInOriRequest buildRequest(HttpServletRequest httpServletRequest) {

        Map<String, String> params = new HashMap<>();
        for (String key : httpServletRequest.getParameterMap().keySet()) {
            params.put(key, httpServletRequest.getParameter(key));
        }

        return new ServiceInOriRequest(params);

    }

    public static ServiceInOriRequest buildRequest(String reqJson) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, String> reqMap = objectMapper.readValue(reqJson, new TypeReference<>() {
        });

        return new ServiceInOriRequest(reqMap);

    }


    public static ServiceInOriRequest buildRequest(DomainEvent domainEvent) {

        Map<String, String> params = new HashMap<>();


        return new ServiceInOriRequest(params);

    }

}
