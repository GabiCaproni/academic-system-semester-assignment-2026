package org.example.academic.system.model;

import jakarta.validation.constraints.NotBlank;
import org.example.academic.system.exception.InvalidAcademicClassException;

import java.util.ArrayList;
import java.util.List;

public class AcademicClass {

    @NotBlank(message = "Código da turma é obrigatório.")
    private String code;

    @NotBlank(message = "Nome da turma é obrigatório.")
    private String name;

    private List<Assessment> assessments;

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

    public void addAssessment(Assessment assessment) {
        assessments.add(assessment);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public List<Assessment> getAssessments() {
        return assessments;
    }

    @Override
    public String toString() {
        return code + " - " + name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AcademicClass other = (AcademicClass) obj;
        return code.equals(other.code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }
}
