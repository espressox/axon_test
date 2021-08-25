package com.example.axon_test.ds.bean.in;


import com.example.axon_test.ds.bean.Result;
import com.example.axon_test.ds.bean.ResultBase;
import com.example.axon_test.ds.bean.ResultSetter;

/**
 * 请求的响应基类
 */
public class ServiceInResponseParam extends ServiceInParam implements Result, ResultSetter {

    /**
     * 结果类
     */
    private final ResultBase result;

    /**
     * @param resultCode String
     * @param resultMsg String
     */
    public ServiceInResponseParam(String resultCode, String resultMsg) {

        result = new ResultBase(resultCode, resultMsg);

    }

    @Override
    public String getResultCode() {

        return result.getResultCode();
    }

    @Override
    public void setResultCode(String resultCode) {

        this.result.setResultCode(resultCode);
    }


    @Override
    public String getResultMsg() {
        return result.getResultMsg();
    }


    @Override
    public void setResultMsg(String resultMsg) {

        result.setResultMsg(resultMsg);
    }


    @Override
    public boolean isSuccess() {

        return result != null && result.isSuccess();
    }

    @Override
    public void setSuccess(boolean success) {

        this.result.setSuccess(success);
    }

}

