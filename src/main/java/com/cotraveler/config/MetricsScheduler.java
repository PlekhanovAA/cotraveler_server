/*
 * Copyright (c)  Aleksey Plekhanov 03.02.2023, 03:14
 */

package com.cotraveler.config;

import com.cotraveler.services.OfferService;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MetricsScheduler {
    private final AtomicInteger totalOffersGauge;
    private final AtomicInteger activeOffersGauge;
    @Autowired
    OfferService offerService;

    public MetricsScheduler(MeterRegistry meterRegistry) {
        totalOffersGauge = meterRegistry.gauge("total_offers", new AtomicInteger(0));
        activeOffersGauge = meterRegistry.gauge("active_offers", new AtomicInteger(0));
    }

    @Scheduled(cron = "${cronIntervalMetricRefreshTime}")
    public void schedulingTask() {
        totalOffersGauge.set((int) offerService.getTotalOffers());
        activeOffersGauge.set(offerService.getActiveOffers());
    }

}
