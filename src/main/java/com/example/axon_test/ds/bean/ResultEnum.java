package com.example.axon_test.ds.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * 结果码
 * @author XinWei
 */
@Getter
public enum ResultEnum {

    /**
     * 成功
     */
    SUCCESS("0", "success"),

    /**
     * 系统异常
     */
    SYSTEM_ERROR("1", "系统异常"),

    /**
     * 执行器不存在
     */
    NO_SUCH_EXECUTOR("2", "%s对应执行器不存在"),

    /**
     * 转换器不存在
     */
    NO_SUCH_CONVERTER("3", "%s对应converter不存在"),

    /**
     * 调用API异常
     */
    APP_EXP_EXECUTOR("4", "调用API异常");



    /**
     * 错误码
     */
    private final String resultCode;

    /**
     * 错误描述
     */
    private final String resultMsg;

    /**
     *
     * @param resultCode String
     * @param resultMsg String
     */
    ResultEnum(String resultCode, String resultMsg) {

        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }


}

