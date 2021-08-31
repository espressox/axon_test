package com.example.axon_test.ds.factory;


import com.example.axon_test.ds.bean.ServiceAPIKeys;
import com.example.axon_test.ds.bean.in.ServiceInModelRequest;
import com.example.axon_test.ds.bean.in.ServiceInOriRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * 请求工厂类
 *
 * @author xin
 */
@AllArgsConstructor
public class ServiceInModelReqFactory {


    /**
     * 请求参数->ServiceInModelRequest
     *
     * @param serviceInOriRequest ServiceInOriRequest
     * @return ServiceInModelRequest
     */
    public static <B> ServiceInModelRequest<B> buildRequest(@NotNull ServiceInOriRequest serviceInOriRequest) throws JsonProcessingException, ClassNotFoundException {

        Map<String, String> requestParams = serviceInOriRequest.getRequestParams();

        ServiceInModelRequest<B> inRequest = new ServiceInModelRequest<>();

        if (requestParams.containsKey("ORI_REQ")) {
            ObjectMapper objectMapper = new ObjectMapper();
            inRequest = objectMapper.readValue(requestParams.get("ORI_REQ"), new TypeReference<>() {
            });
        } else {
            inRequest.setAppId(requestParams.get(ServiceAPIKeys.APP_ID));
            inRequest.setFormat(requestParams.get(ServiceAPIKeys.FORMAT));
            inRequest.setCharset(requestParams.get(ServiceAPIKeys.CHARSET));
            inRequest.setApi(requestParams.get(ServiceAPIKeys.API));
            inRequest.setSign(requestParams.get(ServiceAPIKeys.SIGN));
            inRequest.setSignType(requestParams.get(ServiceAPIKeys.SIGN_TYPE));
            inRequest.setTimestamp(requestParams.get(ServiceAPIKeys.TIMESTAMP));
            inRequest.setVersion(requestParams.get(ServiceAPIKeys.VERSION));
            inRequest.setAuthToken(requestParams.get(ServiceAPIKeys.AUTH_TOKEN));

            String bizStr = requestParams.get(ServiceAPIKeys.ServiceInServiceKeys.BIZ_CONTENT);

            if (null == requestParams.get(ServiceAPIKeys.FORMAT) || "json".equalsIgnoreCase(requestParams.get(ServiceAPIKeys.FORMAT))) {
                ObjectMapper objectMapper = new ObjectMapper();
                B biz = objectMapper.readValue(bizStr, new TypeReference<>() {
                });
                inRequest.setBizContent(biz);
            } else {
                inRequest.setBizContent(null);
            }
        }

        return inRequest;
    }

}

