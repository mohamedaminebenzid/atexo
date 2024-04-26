package com.atexo.identifiergenerator.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString(callSuper = true)
@Schema
public class StringCriterionConfiguration extends CriterionConfiguration {

    private int length;

    @Override
    public String format(Object fieldValue) {
        if (fieldValue instanceof String string) {
            return Objects.toString(getPrefix(), "") + string.toUpperCase().substring(0, Math.min(string.length(), length)) + Objects.toString(getSuffix(), "");
        }
        return "";
    }
}
