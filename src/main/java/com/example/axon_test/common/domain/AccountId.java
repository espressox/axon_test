package com.example.axon_test.common.domain;


import java.io.Serializable;

/**
 * @author xin
 */

public interface AccountId extends Serializable {
    /**
     * Primary Key
     * @return Long identifier
     */
    Long getIdentifier();
}
