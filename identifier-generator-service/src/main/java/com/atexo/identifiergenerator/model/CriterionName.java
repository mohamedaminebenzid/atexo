package com.atexo.identifiergenerator.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public enum CriterionName {
    NAME("name"), FIRST_NAME("firstName"), BIRTH_DATE("birthDate"), COUNTER("counter");
    public final String fieldName;

    CriterionName(String fieldName) {
        this.fieldName = fieldName;
    }
}
