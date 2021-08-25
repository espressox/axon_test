package com.example.axon_test.ds.process;

import com.example.axon_test.ds.bean.process.Instruction;

/**
 *
 * @author XinWei
 * @date 2015/6/23
 *
 * 服务执行器
 */

public interface ServiceExecutor {

    /**
     * 执行操作
     *
     * @param instruction Instruction
     */
    public void doAction(Instruction instruction);
}

