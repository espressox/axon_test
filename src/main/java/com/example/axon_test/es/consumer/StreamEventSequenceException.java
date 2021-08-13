package com.example.axon_test.es.consumer;

/**
 * @author xin
 */
public class StreamEventSequenceException extends RuntimeException {
    public StreamEventSequenceException(String message) {
        super(message);
    }
}
