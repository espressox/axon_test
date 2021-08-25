package com.example.axon_test.ds.process;


import com.example.axon_test.ds.bean.process.Instruction;

/**
 *
 * @author XinWei
 * @date 2015/6/23
 *
 * 服务接口
 */
public interface Service {
    /**
     * 执行当前操作
     *
     * @param instruction Instruction
     */
    public void doBefore(Instruction instruction);

    /**
     * 服务处理
     *
     * @param instruction Instruction
     */
    public void doAction(Instruction instruction);

    /**
     * 执行完毕处理
     *
     * @param instruction Instruction
     */
    public void doFinal(Instruction instruction);

    /**
     * 系统异常时处理
     *
     * @param instruction Instruction
     * @param  e Exception
     */
    public void doFail(Instruction instruction, Exception e);
}
