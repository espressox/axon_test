package com.example.axon_test.ds.process.in;

;
import com.example.axon_test.ds.bean.ResultEnum;
import com.example.axon_test.ds.bean.ServiceAPIKeys;
import com.example.axon_test.ds.bean.in.*;
import com.example.axon_test.ds.bean.process.Instruction;
import com.example.axon_test.ds.exception.ProcessException;
import com.example.axon_test.ds.factory.ServiceInModelReqFactory;
import com.example.axon_test.ds.process.ServiceEngineTemplate;
import com.example.axon_test.ds.process.ServiceExecutor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;


/**
 * 对内请求服务处理引擎
 *
 * @author XinWei
 * @version $Id: ServiceEngine.java, v 0.1 $
 */
@Component
@Slf4j
@AllArgsConstructor
@Getter
@Setter
public class InServiceEngine extends ServiceEngineTemplate {

    /**
     * 请求服务执行器注入
     */
    @Resource
    private Map<String, InServiceExecutor> executorMap;

    /**
     * 执行前操作
     *
     * @param instruction Instruction
     */
    @Override
    public void doBefore(Instruction instruction) {
        InProcessContext inProcessContext = instruction.getInstruction(InProcessContext.class);
        ServiceInOriRequest serviceInOriRequest = instruction.getInstruction(ServiceInOriRequest.class);

        ServiceInModelRequest serviceInModelRequest = null;
        String serviceMsg = null;
        String method = null;
        try {
            // 2.请求转换
            serviceMsg = serviceInOriRequest.getRequestParams().get(ServiceAPIKeys.ServiceInServiceKeys.BIZ_CONTENT);
            method = serviceInOriRequest.getRequestParams().get(ServiceAPIKeys.METHOD);

            serviceInModelRequest = ServiceInModelReqFactory
                    .buildRequest(serviceInOriRequest.getRequestParams(), serviceMsg);

            inProcessContext.setServiceInModelRequest(serviceInModelRequest);
            log.info("InServiceEngine Original Request[" + serviceInOriRequest + "]");
        } catch (Exception e) {
            if (null == serviceInModelRequest) {
                serviceInModelRequest = new ServiceInModelRequest();
                serviceInModelRequest.setMethod(method);
                serviceInModelRequest.setBizContent(serviceMsg);
                inProcessContext.setServiceInModelRequest(serviceInModelRequest);
            }
            log.error("InServiceEngine ERROR[" + e.getMessage() + "]");
            throw new ProcessException(e, ResultEnum.SYSTEM_ERROR);
        }

    }

    /**
     * 服务处理
     *
     * @param instruction Instruction
     */
    @Override
    public void doAction(Instruction instruction) {
        //1.获取请求
        InProcessContext inProcessContext = instruction.getInstruction(InProcessContext.class);
        ServiceInModelRequest serviceInModelRequest = inProcessContext.getInstruction(ServiceInModelRequest.class);
        //2.获取服务
        ServiceExecutor serviceExecutor = executorMap.get(serviceInModelRequest.getMethod());

        //3.执行服务
        if (serviceExecutor == null) {
            ServiceInOriResponse serviceInOriResponse = new ServiceInOriResponse(
                    ResultEnum.NO_SUCH_EXECUTOR.getResultCode(),
                    ResultEnum.NO_SUCH_EXECUTOR.getResultMsg());
            inProcessContext.setServiceInOriResponse(serviceInOriResponse);

            log.warn("当前API执行引擎不存在.[executorName="
                    + serviceInModelRequest.getMethod() + "]");

            throw new ProcessException(ResultEnum.NO_SUCH_EXECUTOR.getResultCode(),
                    String.format(ResultEnum.NO_SUCH_EXECUTOR.getResultMsg(),
                            serviceInModelRequest.getMethod()));
        } else {
            serviceExecutor.doAction(instruction);
        }


    }

    /**
     * 执行完毕处理
     */
    @Override
    public void doFinal(Instruction instruction) {

        InProcessContext inProcessContext = instruction
                .getInstruction(InProcessContext.class);
        ServiceInModelResponse serviceInModelResponse = inProcessContext
                .getInstruction(ServiceInModelResponse.class);

        String serviceOriMsg = null;
        String resultCode = null;
        String resultMsg = null;

        try {

            resultCode = serviceInModelResponse.getResultCode();
            resultMsg = serviceInModelResponse.getResultMsg();
            // 1.返回数据处理
            serviceOriMsg = serviceInModelResponse.getServiceMsg();

        } catch (Exception e) {

            // TODO add log
            resultCode = ResultEnum.SYSTEM_ERROR.getResultCode();
            resultMsg = ResultEnum.SYSTEM_ERROR.getResultMsg();

        } finally {

            // 2.转换最终结果
            ServiceInOriResponse serviceInOriResponse = new ServiceInOriResponse(
                    resultCode, resultMsg);
            serviceInOriResponse.setOriServiceMsg(serviceOriMsg);

            inProcessContext.setServiceInOriResponse(serviceInOriResponse);
        }

    }

    /**
     * 系统异常时处理
     *
     * @param instruction Instruction
     * @param e           Exception
     */
    @Override
    public void doFail(Instruction instruction, Exception e) {

        InProcessContext inProcessContext = instruction
                .getInstruction(InProcessContext.class);
        ServiceInModelRequest serviceInModelRequest = inProcessContext
                .getInstruction(ServiceInModelRequest.class);

        String resultCode;

        String resultMsg;

        if (null == serviceInModelRequest) {
            serviceInModelRequest = new ServiceInModelRequest();
            serviceInModelRequest.setMethod("");
        }

        if (e instanceof ProcessException) {

            resultCode = ((ProcessException) e).getResultCode();
            resultMsg = ((ProcessException) e).getResultMsg();
        } else {

            resultCode = ResultEnum.SYSTEM_ERROR.getResultCode();
            resultMsg = ResultEnum.SYSTEM_ERROR.getResultMsg();
        }

        ServiceInModelResponse failResponse = ServiceInModelResponse.buildFailResponse(resultCode, resultMsg);
        // TODO 修改对应异常文案:这里可是写个工厂类，根据resultCode来回复对应结果
        failResponse.setServiceMsg("");
        inProcessContext.setServiceInModelResponse(failResponse);

    }


}

