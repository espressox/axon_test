package com.example.axon_test.ds.bean.in;


import com.example.axon_test.ds.bean.process.BeanWire;
import com.example.axon_test.ds.bean.process.ProcessContext;
import lombok.Getter;
import lombok.Setter;

/**
 * 请求->处理流程上下文
 *
 * @author XinWei
 * @version $Id: InProcessContext.java, v 0.1 $
 */
@Getter
@Setter
public class InProcessContext extends ProcessContext {
    /**
     * 原始请求
     */
    private ServiceInOriRequest  serviceInOriRequest;

    /**
     * 原始请求的响应
     */
    private ServiceInOriResponse serviceInOriResponse;

    /**
     * 请求:当前业务请求
     */
    private ServiceInModelRequest serviceInModelRequest;

    /**
     * 请求的对应响应:当前业务响应
     */
    private ServiceInModelResponse serviceInModelResponse;


    /**
     * 获取一个实例
     *
     * @return InProcessContext
     */
    public static InProcessContext getInstance() {

        return new InProcessContext();

    }

    /**
     * @param serviceInOriRequest ServiceInOriRequest
     */
    public InProcessContext build(ServiceInOriRequest serviceInOriRequest ) {

        this.setServiceInOriRequest(serviceInOriRequest);
        return this;

    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends BeanWire> T getInstruction(Class<T> t) {

        if (t == ServiceInModelRequest.class) {

            return (T) this.serviceInModelRequest;
        } else if (t == ServiceInModelResponse.class) {

            return (T) this.serviceInModelRequest;
        } else if (t == ServiceInOriRequest.class) {

            return (T) this.serviceInOriRequest;
        } else if (t == ServiceInOriResponse.class) {

            return (T) this.serviceInOriResponse;
        } else if (t == InProcessContext.class) {

            return (T) this;
        }

        return null;
    }
}