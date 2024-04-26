package com.atexo.configuration.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.Positive;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "criterionType", visible = true)
@JsonSubTypes({@Type(value = StringCriterionConfiguration.class, name = "String"), @Type(value = NumberCriterionConfiguration.class, name = "Number"), @Type(value = DateCriterionConfiguration.class, name = "Date")})
@Getter
@Setter
@EqualsAndHashCode
@ToString
public abstract class CriterionConfiguration implements Comparable<CriterionConfiguration> {
    private CriterionName criterionName;
    private String prefix;
    private String suffix;
    private String criterionType;
    @Positive
    private int order;

    @Override
    public int compareTo(CriterionConfiguration criterionConfiguration) {
        return Integer.compare(order, criterionConfiguration.order);
    }
}
