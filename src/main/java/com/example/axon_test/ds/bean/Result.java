
package com.example.axon_test.ds.bean;

/**
 * 结果接口
 * 
 * @author XinWei
 * @version $Id: Reault.java, v 0.1$
 */
public interface Result {

    /**
     * 结果是否成功
     * 
     * @return boolean
     */
    public boolean isSuccess();

    /**
     * Getter method for property <tt>resultCode</tt>.
     * 
     * @return property value of resultCode
     */
    public String getResultCode();

    /**
     * Getter method for property <tt>resultMsg</tt>.
     * 
     * @return property value of resultMsg
     */
    public String getResultMsg();

}
