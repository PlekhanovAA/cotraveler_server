/*
 * Copyright (c)  Aleksey Plekhanov 03.02.2023, 03:14
 */

package com.cotraveler.controllers;

import com.cotraveler.services.LabelService;
import com.cotraveler.services.OfferService;
import com.cotraveler.utils.JSONObjectBuilder;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/map")
public class MapController {
    Logger logger = LoggerFactory.getLogger(MapController.class);

    @Autowired
    private LabelService labelService;

    @Autowired
    private OfferService offerService;

    @GetMapping
    public ResponseEntity<String> showRootPathStatus() {
        return new ResponseEntity<>("map is OK", HttpStatus.OK);
    }

    @GetMapping("/city")
    public String getCityLabel(@RequestParam(name = "cityName") String cityName) {
        logger.info("GET: /map/city for city name: {}", cityName);
        return labelService.getLabelForCity(cityName);
    }

    @GetMapping("/availabilityLabel")
    public JSONObject checkCountOffersForLabel(@RequestParam(name = "labelName") String fromLabelName) {
        logger.info("GET: /map/availabilityLabel for label name: {}", fromLabelName);
        Boolean resultCheck = offerService.checkCountOffersForLabel(fromLabelName);
        return new JSONObjectBuilder().
                addKeyValue("availabilityLabel", Boolean.toString(resultCheck)).
                buildJSONObject();
    }

}
