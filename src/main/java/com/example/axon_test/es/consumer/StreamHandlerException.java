package com.example.axon_test.es.consumer;

/**
 * @author xin
 */
public class StreamHandlerException extends RuntimeException {

    public StreamHandlerException(String message) {
        super(message);
    }

    public StreamHandlerException(String message, Throwable cause) {
        super(message, cause);
    }
}
