
package com.example.axon_test.ds.bean;

/**
 * 
 * @author XinWei
 * @version $Id: ResultSetter.java, v 0.1$
 */
public interface ResultSetter {

    /**
     * 设置成功标识
     * 
     * @param success boolean
     *
     */
    public void setSuccess(boolean success);

    /**
     * Setter method for property <tt>resultCode</tt>.
     * 
     * @param resultCode value to be assigned to property resultCode
     */
    public void setResultCode(String resultCode);

    /**
     * Setter method for property <tt>resultMsg</tt>.
     * 
     * @param resultMsg value to be assigned to property resultMsg
     */
    public void setResultMsg(String resultMsg);

}
