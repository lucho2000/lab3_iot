package com.example.lab3_iot.entity;

import java.util.LinkedHashMap;
import java.util.Map;

public class Timezone {


    private String description;

    private String offset;

    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}
