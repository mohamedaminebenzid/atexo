package com.atexo.configuration.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode
@Schema
public class StringCriterionConfiguration extends CriterionConfiguration {
    @Min(1)
    @Max(10)
    private int length;
}
