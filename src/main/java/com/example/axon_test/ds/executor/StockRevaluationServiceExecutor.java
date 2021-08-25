package com.example.axon_test.ds.executor;

import com.example.axon_test.ds.bean.process.Instruction;
import com.example.axon_test.ds.process.in.InServiceExecutor;
import org.springframework.stereotype.Component;

/**
 * @author xin
 */
@Component
public class StockRevaluationServiceExecutor extends InServiceExecutor {
    @Override
    public void doAction(Instruction instruction) {
        //TODO 1 从Instruction中获取产品持仓信息
        //TODO 2 拿到估值方法
        //TODO 2.1(maybe) 先计算好成本等行情服务需要的参数
        //TODO 传入估值方法 获取产品行情信息 List<PricingDTO> PricingService.getPrice(List<PricingDTO>)
        //TODO PricingDTO {成本，等其它需要计算的参数} getPriceBond(复杂参数列表);

    }

    @Override
    public String getExecutorName() {
        return null;
    }
}
