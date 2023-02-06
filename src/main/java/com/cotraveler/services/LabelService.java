/*
 * Copyright (c)  Aleksey Plekhanov 03.02.2023, 03:14
 */

package com.cotraveler.services;

import com.cotraveler.utils.JsonUtil;
import com.cotraveler.exceptions.CustomException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LabelService {
    Logger logger = LoggerFactory.getLogger(LabelService.class);
    @Autowired
    private JsonUtil jsonUtil;

    public String getLabelForCity(String cityName) {
        for (Object object : (JSONArray) jsonUtil.getCitiesJSON().get("cities")) {
            JSONObject jsonObject = (JSONObject) object;
            if (jsonObject.get("cityName").equals(cityName)) {
                return jsonObject.toJSONString();
            }
        }
        logger.error("City {} not found.", cityName);
        throw new CustomException("City " + cityName + " not found.");
    }

}
