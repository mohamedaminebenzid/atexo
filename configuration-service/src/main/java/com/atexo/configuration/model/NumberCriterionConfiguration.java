package com.atexo.configuration.model;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class NumberCriterionConfiguration extends CriterionConfiguration {
    @Min(5)
    private int length;
}
