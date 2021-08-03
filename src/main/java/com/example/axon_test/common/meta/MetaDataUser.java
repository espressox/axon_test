package com.example.axon_test.common.meta;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xin
 */
@Getter
@Setter
@Builder(builderClassName = "MetaDataUser")
public class MetaDataUser implements MetaDataUserInterface {

    private String name;

    private Long userId;

    private Long customerId;
}