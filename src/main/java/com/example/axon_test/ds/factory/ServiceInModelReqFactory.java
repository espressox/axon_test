package com.example.axon_test.ds.factory;


import com.example.axon_test.ds.bean.ServiceAPIKeys;
import com.example.axon_test.ds.bean.in.ServiceInModelRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * 请求工厂类
 *
 * @author xin
 */
public class ServiceInModelReqFactory {

    /**
     * 请求参数->ServiceInModelRequest
     *
     * @param requestParams Map
     * @param serviceMsg    String
     * @return ServiceInModelRequest
     */
    public static <B> ServiceInModelRequest<B> buildRequest(Map<String, String> requestParams, String serviceMsg, B biz) throws JsonProcessingException {


        if (null != requestParams.get(ServiceAPIKeys.METHOD) && null != serviceMsg) {
            ServiceInModelRequest<B> inRequest = new ServiceInModelRequest<>();
            inRequest.setChannelId(requestParams.get(ServiceAPIKeys.CHANNEL_ID));
            inRequest.setFormat(requestParams.get(ServiceAPIKeys.FORMAT));
            inRequest.setCharset(requestParams.get(ServiceAPIKeys.CHARSET));
            inRequest.setMethod(requestParams.get(ServiceAPIKeys.METHOD));
            inRequest.setSign(requestParams.get(ServiceAPIKeys.SIGN));
            inRequest.setSignType(requestParams.get(ServiceAPIKeys.SIGN_TYPE));
            inRequest.setTimestamp(requestParams.get(ServiceAPIKeys.TIMESTAMP));
            inRequest.setVersion(requestParams.get(ServiceAPIKeys.VERSION));
            inRequest.setAuthToken(requestParams.get(ServiceAPIKeys.AUTH_TOKEN));

            if (null == requestParams.get(ServiceAPIKeys.FORMAT) || "json".equalsIgnoreCase(requestParams.get(ServiceAPIKeys.FORMAT))) {
                ObjectMapper objectMapper = new ObjectMapper();
                biz = objectMapper.readValue(serviceMsg, new TypeReference<>() {
                });
                inRequest.setBizContent(biz);
            }


            return inRequest;
        }


        return null;
    }

}

