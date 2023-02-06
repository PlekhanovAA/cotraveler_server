/*
 * Copyright (c)  Aleksey Plekhanov 03.02.2023, 03:14
 */

package com.cotraveler.controllers;

import com.cotraveler.utils.AppConfig;
import com.cotraveler.utils.JSONObjectBuilder;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class ApplicationController {

    Logger logger = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    private AppConfig appConfig;

    @GetMapping
    public ResponseEntity<String> showRootPathStatus() {
        return new ResponseEntity<>("app is OK", HttpStatus.OK);
    }

    @GetMapping("/mobileAppVer")
    public JSONObject getMobileApplicationVersion() {
        logger.info("GET: /app/mobileAppVer");
        return new JSONObjectBuilder().
                addKeyValue("versionCode", appConfig.getMobileVersionCode()).
                addKeyValue("versionName", appConfig.getMobileVersionName()).
                buildJSONObject();
    }

}
