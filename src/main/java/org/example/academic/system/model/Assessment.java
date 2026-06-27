package org.example.academic.system.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.example.academic.system.exception.InvalidAssessmentException;

@Getter
@Setter
@NoArgsConstructor
public abstract class Assessment {

@NotBlank(message = "Nome da avaliação é obrigatório.")
private String name;

@Positive(message = "Peso deve ser maior que zero.")
private double weight;

@Positive(message = "Valor deve ser maior que zero.")
private double value;

public Assessment(String name, double weight, double value) {

    if (name == null || name.isBlank()) {
        throw new InvalidAssessmentException(
                "Nome da avaliação é obrigatório.");
    }

    if (weight <= 0) {
        throw new InvalidAssessmentException(
                "Peso deve ser maior que zero.");
    }

    if (value <= 0) {
        throw new InvalidAssessmentException(
                "Valor deve ser maior que zero.");
    }

    this.name = name;
    this.weight = weight;
    this.value = value;
}
}
