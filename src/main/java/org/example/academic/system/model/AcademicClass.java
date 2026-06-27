package org.example.academic.system.model;

import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import org.example.academic.system.exception.InvalidAcademicClassException;

import java.util.ArrayList;
import java.util.List;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "code")
public class AcademicClass {

@NotBlank(message = "Código da turma é obrigatório.")
private final String code;

@NotBlank(message = "Nome da turma é obrigatório.")
private final String name;

private final List<Assessment> assessments;

public AcademicClass(String code, String name) {

    if (code == null || code.isBlank()) {
        throw new InvalidAcademicClassException(
                "Código da turma é obrigatório.");
    }

    if (name == null || name.isBlank()) {
        throw new InvalidAcademicClassException(
                "Nome da turma é obrigatório.");
    }

    this.code = code;
    this.name = name;
    this.assessments = new ArrayList<>();
}

public void addAssessment(
        Assessment assessment) {

    assessments.add(assessment);
}

@Override
public String toString() {

    return code + " - " + name;
}

}
