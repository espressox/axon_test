
package com.example.axon_test.ds.bean.in;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 原始请求参数
 * 
 * @author XinWei
 * @version $Id: ServiceInTargetRequest.java, v 0.1 $
 */
@Getter
@Setter
public class ServiceInOriRequest extends ServiceInParam {

    /**
     * 请求参数集合
     */
    private Map<String, String> requestParams;



    /**
     * @param requestParams Map<String, Object>
     */
    public ServiceInOriRequest(Map<String, String> requestParams) {
        super();
        this.requestParams = requestParams;
    }

}
