/*
 * Copyright (c)  Aleksey Plekhanov 03.02.2023, 03:14
 */

package com.cotraveler.controllers;

import com.cotraveler.entities.Offer;
import com.cotraveler.services.OfferService;
import com.cotraveler.utils.AppConfig;
import com.cotraveler.utils.JSONObjectBuilder;
import com.cotraveler.utils.JsonUtil;
import com.cotraveler.utils.OfferUtil;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/passenger")
public class PassengerController {
    Logger logger = LoggerFactory.getLogger(PassengerController.class);

    @Autowired
    private OfferService offerService;

    @Autowired
    private OfferUtil offerUtil;

    @Autowired
    private JsonUtil jsonUtil;

    @Autowired
    private AppConfig appConfig;

    @GetMapping
    public ResponseEntity<String> showRootPathStatus() {
        return new ResponseEntity<>("passenger is OK", HttpStatus.OK);
    }

    @PostMapping("/startOffer")
    public Offer startOffer(@RequestBody Offer offer) {
        logger.info("POST: /passenger/startOffer for offer: " + offer);
        offer.setCreateTime(new Date());
        return offerService.saveOffer(offer);
    }

    @PostMapping("/finishOffer")
    public Offer finishOrder(@RequestBody Offer offer) {
        logger.info("POST: /passenger/finishOffer for offer: " + offer);
        return offerService.saveOffer(offerUtil.prepareForManualClosing(offer));
    }

    @PostMapping("/finishAllOffers")
    public JSONObject finishAllOffers(@RequestBody JSONObject clientIdJSONObject) {
        logger.info("POST: /passenger/finishAllOffers");
        for (Offer offer : offerService.getAllOffersByClientId(clientIdJSONObject.get("clientId").toString())) {
            offerService.saveOffer(offerUtil.prepareForAutoClosing(offer));
        }
        return new JSONObjectBuilder().
                addKeyValue("result", "OK").
                buildJSONObject();
    }

    @PostMapping("/possibilityPassenger")
    public JSONObject checkCountPassengerOfferPerTime(@RequestBody JSONObject clientIdJSONObject) {
        logger.info("POST: /passenger/possibilityPassenger");
        Boolean resultCheck = offerService.checkCountPassengerOfferPerTime(clientIdJSONObject.get("clientId").toString());
        return new JSONObjectBuilder().
                addKeyValue("possibilityPassenger", Boolean.toString(resultCheck)).
                buildJSONObject();
    }

    @GetMapping("/offerLifetime")
    public JSONObject getLifetimeMin() {
        logger.info("GET: /passenger/offerLifetime");
        return new JSONObjectBuilder().
                addKeyValue("offerLifetime", appConfig.getOfferLifetime()).
                buildJSONObject();
    }

    @GetMapping(value = "/getBanner", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getImageWithMediaType() throws IOException {
        logger.info("GET: /passenger/getBanner");
        return IOUtils.toByteArray(jsonUtil.getInputStreamForResource("banner.png"));
    }

}
