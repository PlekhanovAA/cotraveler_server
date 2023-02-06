/*
 * Copyright (c)  Aleksey Plekhanov 03.02.2023, 03:14
 */

package com.cotraveler.utils;

import com.cotraveler.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DatabaseScheduler {

    @Autowired
    OfferService offerService;


    @Scheduled(cron = "${cronIntervalFinishTime}")
    public void scheduleForInsertFinishTimeInOldOffers() {
        offerService.insertFinishTimeForOldOffers();
    }

    @Scheduled(cron = "${cronIntervalDeleting}")
    public void scheduleForDeleteOldOffers() {
        offerService.deleteOldOffers();
    }
}
