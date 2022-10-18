package com.sample.projects.camelexplorer.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MagicCard {

    @JsonProperty("name")
    private String name;

    @Override
    public String toString() {
        return "MagicCard{" +
                "name='" + name + '\'' +
                '}';
    }
}
