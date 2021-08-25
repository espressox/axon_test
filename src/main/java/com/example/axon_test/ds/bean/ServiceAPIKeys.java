package com.example.axon_test.ds.bean;

/**
 *交互服务的key值
 * @author xin
 */
public final class ServiceAPIKeys {

    /**
     * 渠道ID
     */
    public final static String CHANNEL_ID = "channelId";

    /**
     * 调用的服务
     */
    public final static String METHOD = "method";

    /**
     * JSON or XML
     */
    public final static String FORMAT = "format";
    /**
     * 字符集
     */
    public final static String CHARSET = "charset";
    /**
     * 签名类型 RSA
     */
    public final static String SIGN_TYPE = "signType";
    /**
     * 签名
     */
    public final static String SIGN = "sign";
    /**
     * 时间戳
     */
    public final static String TIMESTAMP = "timestamp";
    /**
     * 服务版本
     */
    public final static String VERSION = "version";
    /**
     * 授权Token
     */
    public final static String AUTH_TOKEN = "authToken";

    /**
     * 请求内容
     */
    public static class ServiceInServiceKeys {

        /**
         * 业务参数
         */
        public final static String BIZ_CONTENT = "bizContent";
    }

}

