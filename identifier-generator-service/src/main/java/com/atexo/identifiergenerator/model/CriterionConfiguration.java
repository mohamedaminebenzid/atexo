package com.atexo.identifiergenerator.model;

import com.atexo.identifiergenerator.utils.Formattable;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "criterionType", visible = true)
@JsonSubTypes({
        @Type(value = StringCriterionConfiguration.class, name = "String"),
        @Type(value = NumberCriterionConfiguration.class, name = "Number"),
        @Type(value = DateCriterionConfiguration.class, name = "Date")
})
@Getter
@Setter
@ToString
public abstract class CriterionConfiguration implements Formattable {
    private CriterionName criterionName;
    private String prefix;
    private String suffix;
    private String criterionType;
}

