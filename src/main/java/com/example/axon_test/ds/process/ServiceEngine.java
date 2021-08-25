package com.example.axon_test.ds.process;

import com.example.axon_test.ds.bean.process.Instruction;

/**
 * 服务执行接口
 *
 * @author XinWei
 * @version $Id: ServiceEngine.java, v 0.1
 */
public interface ServiceEngine {
    /**
     * 模板服务，统一处理请求
     *
     * @param instruction Instruction
     */
    public void serviceProcess(Instruction instruction);
}
