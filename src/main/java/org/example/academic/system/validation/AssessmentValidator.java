/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.academic.system.validation;

/**
 *
 * @author Gabi Caproni
 */

import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.model.Assessment;

public class AssessmentValidator {

    public void validate(Assessment assessment) {

        if (assessment.getName() == null ||
            assessment.getName().isBlank()) {

            throw new AcademicSystemException(
                    "Nome da avaliação inválido.");
        }

        if (assessment.getWeight() <= 0) {

            throw new AcademicSystemException(
                    "Peso inválido.");
        }

        if (assessment.getValue() <= 0) {

            throw new AcademicSystemException(
                    "Valor inválido.");
        }
    }
}
