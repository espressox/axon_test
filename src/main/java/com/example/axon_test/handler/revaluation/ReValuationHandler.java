package com.example.axon_test.handler.revaluation;


import com.example.axon_test.command.events.RevaluationEvent;
import com.example.axon_test.ds.bean.in.InProcessContext;
import com.example.axon_test.ds.bean.in.ServiceInOriRequest;
import com.example.axon_test.ds.factory.ServiceInOriReqFactory;
import com.example.axon_test.ds.process.in.InServiceEngine;
import com.example.axon_test.es.consumer.StreamEventHandler;
import com.example.axon_test.es.event.DomainEvent;
import com.example.axon_test.stream.channel.ChannelDefinition;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

/**
 * @author xin
 */
@Component
@Slf4j
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ReValuationHandler {
    @Autowired
    private InServiceEngine inServiceEngine;

    @StreamEventHandler(types = ChannelDefinition.STOCK_REVALUATION)
    public void handle(RevaluationEvent revaluationEvent, DomainEvent<HashMap, HashMap> domainEvent) {
        ServiceInOriRequest serviceInOriRequest = ServiceInOriReqFactory.buildRequest(domainEvent);
        InProcessContext inProcessContext = InProcessContext.getInstance().build(serviceInOriRequest);
        inServiceEngine.serviceProcess(inProcessContext);
    }


}
