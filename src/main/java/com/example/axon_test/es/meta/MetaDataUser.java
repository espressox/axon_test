package com.example.axon_test.es.meta;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xin
 */
@Getter
@Setter
@Builder(builderClassName = "MetaData")
public class MetaDataUser implements MetaDataInterface {

    private String name;

    private Long userId;

    private String channel;

}