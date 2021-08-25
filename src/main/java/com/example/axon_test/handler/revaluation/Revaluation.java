package com.example.axon_test.handler.revaluation;

import com.example.axon_test.handler.BizContent;

/**
 * @author xin
 */
public interface Revaluation<T extends BizContent> {

    /**
     * 估增处理
     * @param o T
     */
    void revaluation(T o);

}
