/*
 * Copyright (c)  Aleksey Plekhanov 03.02.2023, 03:14
 */

package com.cotraveler.utils;

import com.cotraveler.exceptions.CustomException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class JsonUtil {
    Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private JSONObject cities;

    public JSONObject getCitiesJSON() {
        if (cities == null) {
            JSONParser parser = new JSONParser();
            try {
                cities = (JSONObject) parser.parse(new InputStreamReader(getInputStreamForResource("cities.json")));
            } catch (IOException | ParseException e) {
                logger.error("parse cities.json error: {}", e.getMessage());
                throw new CustomException("parse cities.json error: " + e);
            }
        }

        return cities;
    }

    public InputStream getInputStreamForResource(String resourceName) {
        InputStream ioStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(resourceName);

        if (ioStream == null) {
            logger.error("{} is not found", resourceName);
            throw new CustomException(resourceName + " is not found");
        }
        return ioStream;
    }

}
