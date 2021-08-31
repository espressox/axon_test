package com.example.axon_test.ds.bean.in;


import lombok.Getter;
import lombok.Setter;

/**
 * 请求入参类
 * @author xin
 */
@Setter
@Getter
public class ServiceInModelRequest<B> extends ServiceInParam {

    /**
     * 渠道代码
     */
    private String appId;

    /**
     * 服务api名称
     */
    private String api;

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
     * 请求元数据
     */
    private String metaData;

    /**
     * 业务数据容器
     */
    private B bizContent;




}

