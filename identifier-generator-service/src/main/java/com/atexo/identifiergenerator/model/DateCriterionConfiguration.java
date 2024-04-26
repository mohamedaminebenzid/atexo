package com.atexo.identifiergenerator.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString(callSuper = true)
@Schema
public class DateCriterionConfiguration extends CriterionConfiguration {
    @Override
    public String format(Object fieldValue) {
        if(fieldValue instanceof LocalDate date) {
            return Objects.toString(getPrefix(), "") + date.getYear()
                    + Objects.toString(getSuffix(), "");
        }
        return "";
    }
}
