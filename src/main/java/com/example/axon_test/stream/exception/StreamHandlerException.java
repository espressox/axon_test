package com.example.axon_test.stream.exception;

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
