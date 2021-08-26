
package com.example.axon_test.ds.bean.in;


import com.example.axon_test.ds.bean.ResultBase;
import com.example.axon_test.ds.bean.BeanWire;
import lombok.Getter;
import lombok.Setter;

/**
 * 原始请求的响应
 * 
 * @author XinWei
 * @version $Id: ServiceInOriResponse.java, v 0.1$
 */
@Getter
@Setter
public class ServiceInOriResponse extends ResultBase implements BeanWire {

    /**
     * 最终发送的数据
     */
    private String oriServiceMsg;

    /**
     * @param resultCode String
     * @param resultMsg String
     */
    public ServiceInOriResponse(String resultCode, String resultMsg) {

        super(resultCode, resultMsg);
    }

}
