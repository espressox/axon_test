package com.example.axon_test.ds.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * Created by XinWei on 2015/6/24.
 * 字符串toString基类
 *
 * @author XinWei
 * @version $Id: ToStringBase.java v 0.1 $
 */
public class ToStringBase implements Serializable {

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
