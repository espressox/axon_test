package com.example.axon_test.app.customer.executor;

import com.example.axon_test.ds.bean.process.Instruction;
import com.example.axon_test.ds.process.in.InServiceExecutor;
import org.springframework.stereotype.Component;

/**
 * @author xin
 */
@Component
public class CustomerServiceExecutor extends InServiceExecutor {
    @Override
    public void doAction(Instruction instruction) {
        System.out.println("hello customer");
    }

    @Override
    public String getExecutorName() {
        return "journal.customer";
    }
}
