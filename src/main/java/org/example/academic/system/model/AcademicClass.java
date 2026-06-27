/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.academic.system.model;

/**
 *
 * @author Gabi Caproni
 */
import org.example.academic.system.exception.InvalidAcademicClassException;
import java.util.ArrayList;
import java.util.List;

public class AcademicClass {

    private String code;
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

}
