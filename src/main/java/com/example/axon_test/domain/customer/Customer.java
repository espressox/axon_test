package com.example.axon_test.domain.customer;

import com.alibaba.cola.domain.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @author xin
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private String customerId;
    private String memberId;
    private String globalId;
    private Long registeredCapital;
    private String companyName;

    public boolean isBigCompany() {
        return registeredCapital > 10000000; //注册资金大于1000万的是大企业
    }
    public boolean isSME() {
        return registeredCapital > 10000 && registeredCapital < 1000000; //注册资金大于10万小于100万的为中小企业
    }

}
