package com.test.cts.config.scheduler;

import com.test.cts.services.PriceAggregationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class PriceAggregateScheduler {
    @Autowired
    PriceAggregationService priceAggregationService;

    // This method will be scheduled to run every 10 seconds
    @Scheduled(fixedRate = 10000)
    public void aggregatePrices() {
        priceAggregationService.aggregatePrices();
    }
}
