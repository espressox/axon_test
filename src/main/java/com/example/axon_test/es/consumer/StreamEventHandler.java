package com.example.axon_test.es.consumer;

import java.lang.annotation.*;

/**
 * types 对应 Stream 本身 Input 的类型，
 * payloadTypes 对应事件类型，比如 AccountCreated，
 * 我们要做的效果是这个 payloadTypes 可以不写，直接从方法的第一个参数读取 class 的 simpleName
 * @author xin
 */
@Target( {ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StreamEventHandler {

    String[] payloadTypes() default {""};

    String[] types();
}