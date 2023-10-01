package com.example.lab3_iot.entity;

import java.util.LinkedHashMap;
import java.util.Map;

public class Dob {

    private String date;

    private Integer age;

    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}
