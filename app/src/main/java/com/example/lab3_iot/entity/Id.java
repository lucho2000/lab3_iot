package com.example.lab3_iot.entity;

import java.util.LinkedHashMap;
import java.util.Map;

public class Id {

    private String name;

    private String value;

    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}
