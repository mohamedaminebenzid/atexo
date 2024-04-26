package com.atexo.configuration.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Schema
@AllArgsConstructor
@NoArgsConstructor
public class Configuration {
    @PositiveOrZero
    private int counterInitialValue;
    //Sorted set based on Comparable implementation
    @Schema
    private List<@Valid CriterionConfiguration> orderedCriteria;
}
