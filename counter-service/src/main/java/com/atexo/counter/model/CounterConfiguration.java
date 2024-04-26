package com.atexo.counter.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema
@AllArgsConstructor
@NoArgsConstructor
public class CounterConfiguration {
    private int counterInitialValue;
}
