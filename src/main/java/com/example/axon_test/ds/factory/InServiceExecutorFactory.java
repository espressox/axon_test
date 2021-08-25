package com.example.axon_test.ds.factory;

import com.example.axon_test.ds.process.ServiceExecutor;
import com.example.axon_test.ds.process.in.InServiceExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author xin
 */
public class InServiceExecutorFactory {
    /**
     * 服务映射
     */
    private final Map<String, ServiceExecutor> executorMaps = new HashMap<>();

    /**
     * 根据name查询服务执行器
     *
     * @param name String
     * @return ServiceExecutor
     */
    public ServiceExecutor getExecutorByName(String name) {
        return executorMaps.get(name);
    }

    /**
     *  设置服务执行器
     *
     * @param executors List<InServiceExecutor>
     */
    public void setExecutorList(List<InServiceExecutor> executors) {

        if (executors == null || executors.isEmpty()) {

            return;
        }

        for (InServiceExecutor executor : executors) {

            executorMaps.put(executor.getExecutorName(), executor);
        }

    }

}
