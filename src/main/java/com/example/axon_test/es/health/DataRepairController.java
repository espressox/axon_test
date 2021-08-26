package com.example.axon_test.es.health;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

/**
 * @author xin
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/repair")
public class DataRepairController {

    private final ScheduleService scheduleService;

    private static final String SECRET = "e248b98418db4cdcb069e8a1c08f6bb7";

    @GetMapping("/message")
    @Async
    public void repairMessage(@RequestParam("secret") String secret) {
        if (!StringUtils.equals(secret, SECRET)) {
            return;
        }

        scheduleService.failedMessageDiscovery();
    }

    @PostMapping("/aggregate")
    @Async
    public void repairAggregate(@RequestParam("secret") String secret, Long aggregateIdentifier) {
        if (!StringUtils.equals(secret, SECRET)) {
            return;
        }
//        accountViewService.updateViewFromAggregateById(aggregateIdentifier, Instant.now());
    }
}
/**/