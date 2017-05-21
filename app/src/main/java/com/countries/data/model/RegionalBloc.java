package com.countries.data.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tosin Onikute.
 */

public class RegionalBloc {

    private String acronym;
    private String name;
    private List<String> otherAcronyms = null;
    private List<String> otherNames = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOtherAcronyms() {
        return otherAcronyms;
    }

    public void setOtherAcronyms(List<String> otherAcronyms) {
        this.otherAcronyms = otherAcronyms;
    }

    public List<String> getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(List<String> otherNames) {
        this.otherNames = otherNames;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
