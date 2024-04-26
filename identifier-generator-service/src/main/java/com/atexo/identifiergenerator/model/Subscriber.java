package com.atexo.identifiergenerator.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Schema
@Valid
@AllArgsConstructor
@NoArgsConstructor
public class Subscriber {
    @NotBlank
    private String name;
    @NotBlank
    private String firstName;
    @Past
    private LocalDate birthDate;
}
