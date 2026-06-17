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
import org.example.academic.system.model.AcademicClass;

public class AcademicClassValidator {

    public void validate(AcademicClass academicClass) {

        if (academicClass.getCode() == null ||
            academicClass.getCode().isBlank()) {

            throw new AcademicSystemException(
                    "Código da turma inválido.");
        }

        if (academicClass.getName() == null ||
            academicClass.getName().isBlank()) {

            throw new AcademicSystemException(
                    "Nome da turma inválido.");
        }
    }
}
