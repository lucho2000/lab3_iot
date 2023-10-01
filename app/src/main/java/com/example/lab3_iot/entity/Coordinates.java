package com.example.lab3_iot.entity;

import java.util.LinkedHashMap;
import java.util.Map;

public class Coordinates {


    private String latitude;

    private String longitude;

    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();


    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}
