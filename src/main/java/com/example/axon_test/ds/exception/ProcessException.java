package com.example.axon_test.ds.exception;


import com.example.axon_test.ds.bean.ResultEnum;

/**
 * 异常类
 * @author XinWei
 * @version
 */
public class ProcessException extends RuntimeException {

    /**
     * 结果码
     */
    private String resultCode;

    /**
     * 结果描述
     */
    private String resultMsg;

    public ProcessException(Throwable throwAble) {
        super(throwAble);
    }
    /**
     *
     * @param throwAble Throwable
     * @param result ResultEnum
     */
    public ProcessException(Throwable throwAble, ResultEnum result) {

        super(throwAble);
        build(result);
    }

    public ProcessException(Throwable throwAble, String code, String result) {

        super(throwAble);
        this.resultCode = code;
        this.resultMsg = result;
    }

    /**
     * @param resultCode String
     * @param resultMsg String
     */
    public ProcessException(String resultCode, String resultMsg) {

        super();

        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    /**
     *
     * @param resultEnum
     */
    public ProcessException(ResultEnum resultEnum) {

        super();

        build(resultEnum);

    }

    /**
     *
     * @param resultEnum
     */
    private void build(ResultEnum resultEnum) {
        if (resultEnum != null) {

            this.resultCode = resultEnum.getResultCode();
            this.resultMsg = resultEnum.getResultMsg();
        }
    }

    /**
     * Getter method for property <tt>resultCode</tt>.
     *
     * @return property value of resultCode
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * Getter method for property <tt>resultMsg</tt>.
     *
     * @return property value of resultMsg
     */
    public String getResultMsg() {
        return resultMsg;
    }

}
