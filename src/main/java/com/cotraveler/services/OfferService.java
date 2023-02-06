/*
 * Copyright (c)  Aleksey Plekhanov 03.02.2023, 03:14
 */

package com.cotraveler.services;

import com.cotraveler.entities.Offer;
import com.cotraveler.repository.OfferRepository;
import com.cotraveler.utils.AppConfig;
import com.cotraveler.utils.OfferUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OfferService {
    Logger logger = LoggerFactory.getLogger(OfferService.class);

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private OfferUtil offerUtil;

    @Autowired
    private AppConfig appConfig;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Offer saveOffer(Offer offer) {
        logger.info("save offer: {}", offer);
        return offerRepository.save(offer);
    }

    public List<Offer> findByFromLabelNameAndFinishTimeNotNull(String fromLabelName) {
        return offerRepository.findByFromLabelNameAndFinishTimeNull(fromLabelName);
    }

    public List<Offer> getAllOffersByClientId(String clientId) {
        return offerRepository.findByClientIdAndFinishTimeNull(clientId);
    }

    public Boolean checkCountOffersForLabel(String labelName) {
        int count = offerRepository.countByFromLabelNameAndFinishTimeNotNull(labelName);
        return count < Integer.parseInt(appConfig.getCountOffersForLabel());
    }

    public Boolean checkCountPassengerOfferPerTime(String clientId) {
        Date nowMinusTimeInterval = new Date(System.currentTimeMillis() - Long.parseLong(appConfig.getTimeIntervalCreatingOffersMin()) * 60 * 1000);
        int count = offerRepository.countByClientIdAndCreateTimeGreaterThan(clientId, nowMinusTimeInterval);
        return count < Integer.parseInt(appConfig.getFrequencyCreatingOffers());
    }

    public void insertFinishTimeForOldOffers() {
        Date now = new Date();
        Date nowMinusInterval = new Date(now.getTime() - Long.parseLong(appConfig.getTimeIntervalInsertFinishTimeMin()) * 60 * 1000);
        logger.info("try insert finish time less than: {}", dateFormat.format(nowMinusInterval));
        List<Offer> offers = offerRepository.findByFinishTimeNullAndCreateTimeLessThan(nowMinusInterval);
        for (Offer offer : offers) {
            offerRepository.save(offerUtil.prepareForAutoClosing(offer));
            logger.info("insert finish time for: {}", offer);
        }
    }

    public void deleteOldOffers() {
        Date now = new Date();
        Date nowMinusInterval = new Date(now.getTime() - Long.parseLong(appConfig.getTimeIntervalDeletingOldOffersHour()) * 60 * 60 * 1000);
        logger.info("try delete offers time by create time less than: {}", dateFormat.format(nowMinusInterval));
        offerRepository.deleteByCreateTimeLessThan(nowMinusInterval);
    }

    public long getTotalOffers() {
        long totalOffers = offerRepository.count();
        logger.info("total offers: {}", totalOffers);
        return totalOffers;
    }

    public int getActiveOffers() {
        int activeOffers = offerRepository.countByFinishTimeNull();
        logger.info("active offers: {}", activeOffers);
        return activeOffers;
    }

}
