package com.example.axon_test.ds.bean.in;


import lombok.Getter;
import lombok.Setter;

/**
 * 请求相应应答
 *
 * @author xin
 */
@Getter
@Setter
public class ServiceInModelResponse extends ServiceInResponseParam {

    /**
     * 响应应答的数据
     */
    private String serviceMsg;

    /**
     * 构建一个失败的结果
     *
     * @param resultCode String
     * @param resultMsg String
     * @return ServiceInModelResponse
     */
    public static ServiceInModelResponse buildFailResponse(String resultCode, String resultMsg) {

        ServiceInModelResponse serviceInModelResponse = new ServiceInModelResponse(resultCode,
                resultMsg);
        serviceInModelResponse.setSuccess(false);

        return serviceInModelResponse;

    }

    /**
     * @param resultCode String
     * @param resultMsg String
     */
    public ServiceInModelResponse(String resultCode, String resultMsg) {
        super(resultCode, resultMsg);

    }

}

