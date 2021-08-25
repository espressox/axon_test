package com.example.axon_test.handler.revaluation.stock;

import com.example.axon_test.ds.bean.in.InProcessContext;
import com.example.axon_test.ds.bean.in.ServiceInOriRequest;
import com.example.axon_test.ds.factory.ServiceInModelReqFactory;
import com.example.axon_test.ds.process.in.InServiceEngine;
import com.example.axon_test.handler.revaluation.Revaluation;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xin
 */
public class StockRevaluation implements Revaluation<StockRevaluationBizContent> {



    @Override
    public void revaluation(StockRevaluationBizContent o) {


    }
}
