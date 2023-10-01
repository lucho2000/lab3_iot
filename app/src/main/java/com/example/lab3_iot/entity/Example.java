package com.example.lab3_iot.entity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Example {

    private List<Result> results;

    private Info info;

    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}
