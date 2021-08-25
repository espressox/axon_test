
package com.example.axon_test.ds.bean;

import java.io.Serializable;

/**
 * 结果基类
 * 
 * @author XinWei
 * @version $Id: ResultBase.java, v 0.1$
 */
public class ResultBase extends ToStringBase implements Result, ResultSetter, Serializable {


    /**
     * 成功标识
     */
    private boolean           success;

    /**
     * 结果码
     */
    private String            resultCode;

    /**
     * 结果描述
     */
    private String            resultMsg;

    /**
     * @param resultCode String
     * @param resultMsg String
     */
    public ResultBase(String resultCode, String resultMsg) {
        super();
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;

        this.setSuccess(org.apache.commons.lang3.StringUtils.endsWithIgnoreCase(
            ResultEnum.SUCCESS.getResultCode(), this.resultCode));
    }

    /**
     * Getter method for property <tt>resultCode</tt>.
     * 
     * @return property value of resultCode
     */
    @Override
    public String getResultCode() {
        return resultCode;
    }

    /**
     * Setter method for property <tt>resultCode</tt>.
     * 
     * @param resultCode value to be assigned to property resultCode
     */
    @Override
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * Getter method for property <tt>resultMsg</tt>.
     * 
     * @return property value of resultMsg
     */
    @Override
    public String getResultMsg() {
        return resultMsg;
    }

    /**
     * Setter method for property <tt>resultMsg</tt>.
     * 
     * @param resultMsg value to be assigned to property resultMsg
     */
    @Override
    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    /** 
     * @see com.camb.common.web.bean.Result#isSuccess()
     */
    @Override
    public boolean isSuccess() {

        return this.success;
    }

    /** 
     * @see com.camb.common.web.bean.ResultSetter#setSuccess(boolean)
     */
    @Override
    public void setSuccess(boolean success) {

        this.success = success;
    }

}
