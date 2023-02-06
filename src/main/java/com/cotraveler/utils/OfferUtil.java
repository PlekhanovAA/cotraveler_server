/*
 * Copyright (c)  Aleksey Plekhanov 03.02.2023, 03:14
 */

package com.cotraveler.utils;

import com.cotraveler.entities.Offer;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OfferUtil {

    public Offer prepareForManualClosing(Offer offer) {
        offer.setFinishTime(new Date());
        offer.setAutoClosed(false);
        return offer;
    }

    public Offer prepareForAutoClosing(Offer offer) {
        offer.setFinishTime(new Date());
        offer.setAutoClosed(true);
        return offer;
    }
}
