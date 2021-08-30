package com.example.axon_test.ds.factory;


import java.util.HashMap;
import java.util.Map;


/**
 * @author xin
 */
public class ServiceAPIDefFactory {
    /**
     * Model API 映射
     */
    private final Map<String, String> modelMaps = new HashMap<>();
    /**
     * 执行器 Api 映射
     */
    private final Map<String, String> exeApiMaps = new HashMap<>();

    /**
     * 根据name查询model
     *
     * @param apiName String
     * @return String
     */
    public String getModelByApi(String apiName) {
        return modelMaps.get(apiName);
    }

    /**
     * 根据name查询服务执行器
     *
     * @param apiName String
     * @return String
     */
    public String getExeByApi(String apiName) {
        return exeApiMaps.get(apiName);
    }

    /**
     *  设置服务执行器
     */
    public void setModelMap(Map<String, String> modelMap) {

        if (modelMap == null || modelMap.isEmpty()) {

            return;
        }

        modelMaps.putAll(modelMap);

    }

    public void setExeMap(Map<String, String> exeMap) {

        if (exeMap == null || exeMap.isEmpty()) {

            return;
        }

        exeApiMaps.putAll(exeMap);

    }

}
