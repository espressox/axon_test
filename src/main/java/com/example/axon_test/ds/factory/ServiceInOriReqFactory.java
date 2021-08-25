
package com.example.axon_test.ds.factory;


import com.example.axon_test.ds.bean.in.ServiceInOriRequest;
import com.example.axon_test.es.event.DomainEvent;

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

    /**
     *  httpServletRequest -> ServiceInOriRequest
     * 
     * @param httpServletRequest  HttpServletRequest
     * @return ServiceInOriRequest
     */
    public static ServiceInOriRequest buildRequest(HttpServletRequest httpServletRequest) {

        Map<String, String> params = new HashMap<String, String>();
        for (Object key : httpServletRequest.getParameterMap().keySet()) {
            String keyStr = (String) key;
            params.put(keyStr, httpServletRequest.getParameter(keyStr));
        }

        return new ServiceInOriRequest(params);

    }

    public static ServiceInOriRequest buildRequest(DomainEvent domainEvent) {

        Map<String, String> params = new HashMap<String, String>();


        return new ServiceInOriRequest(params);

    }

}
