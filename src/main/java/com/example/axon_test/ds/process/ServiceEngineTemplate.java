package com.example.axon_test.ds.process;


import com.example.axon_test.ds.bean.process.Instruction;
import com.example.axon_test.ds.exception.ProcessException;
import lombok.extern.slf4j.Slf4j;

/**
 * 服务模板类
 *
 * @author XinWei
 * @version $Id: ServiceEngineTemplate.java, v 0.1$
 */
@Slf4j
public class ServiceEngineTemplate implements ServiceEngine, Service {

    @Override
    public final void serviceProcess(Instruction instruction) {

        try {

            doBefore(instruction);

            doAction(instruction);

        } catch (ProcessException de) {

            log.error("服务执行业务异常", de);
            doFail(instruction, de);

        } catch (Exception e) {

            log.error("服务执行系统异常", e);
            doFail(instruction, e);

        } finally {

            doFinal(instruction);
        }

    }

    @Override
    public void doBefore(Instruction instruction) {
    }

    @Override
    public void doAction(Instruction instruction) {
    }

    @Override
    public void doFinal(Instruction instruction) {

    }

    @Override
    public void doFail(Instruction instruction, Exception e) {

    }

}
