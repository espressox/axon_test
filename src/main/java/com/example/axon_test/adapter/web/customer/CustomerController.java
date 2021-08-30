package com.example.axon_test.adapter.web.customer;

import com.alibaba.cola.dto.MultiResponse;
import com.example.axon_test.client.customer.api.CustomerServiceI;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * @author xin
 */
@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerServiceI customerServiceI;

    @PostMapping
    public MultiResponse customerCtrl(@RequestBody String reqJson) throws JsonProcessingException {
        return customerServiceI.customerSvc(reqJson);
    }

}