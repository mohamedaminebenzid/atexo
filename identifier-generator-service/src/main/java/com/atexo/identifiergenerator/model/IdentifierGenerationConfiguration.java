package com.atexo.identifiergenerator.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Schema
@AllArgsConstructor
@NoArgsConstructor
public class IdentifierGenerationConfiguration {
    private int counterInitialValue;
    //Lists preserves insertion order
    private List<CriterionConfiguration> criteria;
}
