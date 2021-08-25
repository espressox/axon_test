package com.example.axon_test.ds.bean.process;

/**
 *
 * @author XinWei
 * @date 2015/6/23
 *
 * 参数获取接口
 */
public interface Instruction {
    /**
     * 根据传入参数类型，转换成对应的参数
     *
     * @param t Class<T>
     * @return T
     */

    <T extends BeanWire> T getInstruction(Class<T> t);
}
