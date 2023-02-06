/*
 * Copyright (c)  Aleksey Plekhanov 03.02.2023, 03:14
 */

package com.cotraveler.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AppConfig {

    @Value("${offer.lifetimeMin}")
    private String offerLifetime;

    @Value("${label.countOffers}")
    private String countOffersForLabel;

    @Value("${passenger.frequencyCreatingOffers}")
    private String frequencyCreatingOffers;

    @Value("${passenger.frequencyCreatingOffers.timeIntervalMin}")
    private String timeIntervalCreatingOffersMin;

    @Value("${offer.intervalInsertFinishTime}")
    private String timeIntervalInsertFinishTimeMin;

    @Value("${offer.intervalDeleting}")
    private String timeIntervalDeletingOldOffersHour;

    @Value("${mobile.version.code}")
    private String mobileVersionCode;

    @Value("${mobile.version.name}")
    private String mobileVersionName;

}
