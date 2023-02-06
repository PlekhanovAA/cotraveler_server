/*
 * Copyright (c)  Aleksey Plekhanov 03.02.2023, 03:14
 */

package com.cotraveler.controllers;

import com.cotraveler.entities.Offer;
import com.cotraveler.services.OfferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriverController {
    Logger logger = LoggerFactory.getLogger(DriverController.class);

    @Autowired
    private OfferService offerService;

    @GetMapping
    public ResponseEntity<String> showRootPathStatus() {
        return new ResponseEntity<>("driver is OK", HttpStatus.OK);
    }

    @GetMapping("/labelOffers")
    public List<Offer> getOrdersForLabel(@RequestParam(name = "labelName") String fromLabelName) {
        logger.info("GET: /driver/labelOffers for label name: {}", fromLabelName);
        return offerService.findByFromLabelNameAndFinishTimeNotNull(fromLabelName);
    }

}
