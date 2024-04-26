package com.atexo.configuration.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.TreeSet;

@Getter
@Schema
@AllArgsConstructor
@NoArgsConstructor
public class Configuration {
    @PositiveOrZero
    private int counterInitialValue;

    @Schema
    //Sorted set based on Comparable implementation
    private TreeSet<@Valid CriterionConfiguration> criteria;
}
