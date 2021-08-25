package com.example.axon_test.ds.bean.in;


import lombok.Getter;
import lombok.Setter;

/**
 * 请求入参类
 * @author xin
 */
@Setter
@Getter
public class ServiceInModelRequest extends ServiceInParam {

    /**
     * 渠道代码
     */
    private String channelId;

    /**
     * 服务api名称
     */
    private String method;

    /**
     * JSON or XML
     */
    private String format;

    /**
     * 签名数据
     */
    private String sign;

    /**
     * 签名类型
     */
    private String signType;

    /**
     * 编码类型
     */
    private String charset;

    /**
     * 时间戳
     */
    private String timestamp;

    /**
     * 服务版本号
     */
    private String version;

    /**
     * 授权token
     */
    private String authToken;

    /**
     * 业务数据
     */
    private String bizContent;

    /**
     * 业务数据对应模型
     */
    private Object inBizContent;

}

