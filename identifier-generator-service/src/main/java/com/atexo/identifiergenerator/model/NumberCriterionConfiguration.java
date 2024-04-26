package com.atexo.identifiergenerator.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString(callSuper=true)
@Schema
public class NumberCriterionConfiguration extends CriterionConfiguration{
    private int length;
    @Override
    public String format(Object fieldValue) {
        if (fieldValue instanceof Integer integer) {
            return Objects.toString(getPrefix(), "") + String.format("%0" + length + "d", integer)
                    + Objects.toString(getSuffix(), "");
        }
        return "";
    }

}
