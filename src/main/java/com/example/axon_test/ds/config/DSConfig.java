package com.example.axon_test.ds.config;

import com.example.axon_test.ds.factory.InServiceExecutorFactory;
import com.example.axon_test.ds.process.in.InServiceExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author xin
 */
@Configuration
public class DSConfig {
    @Bean
    public InServiceExecutorFactory inServiceExecutorFactory(List<InServiceExecutor> inServiceExecutorList) {
        InServiceExecutorFactory inServiceExecutorFactory = new InServiceExecutorFactory();
        inServiceExecutorFactory.setExecutorList(inServiceExecutorList);
        return inServiceExecutorFactory;
    }
}
