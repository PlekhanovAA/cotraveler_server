/*
 * Copyright (c)  Aleksey Plekhanov 03.02.2023, 03:14
 */

package com.cotraveler.utils;

import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JSONObjectBuilder {

    Map<String, String> jsonMap;

    public JSONObjectBuilder() {
        jsonMap = new HashMap<>();
    }

    public JSONObjectBuilder addKeyValue(String key, String value) {
        this.jsonMap.put(key, value);
        return this;
    }

    public JSONObject buildJSONObject() {
        return new JSONObject(jsonMap);
    }
}
