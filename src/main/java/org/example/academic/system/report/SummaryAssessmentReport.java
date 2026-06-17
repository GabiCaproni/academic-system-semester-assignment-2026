/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.academic.system.report;

import org.example.academic.system.model.AcademicClass;

/**
 *
 * @author Gabi Caproni
 */
public class SummaryAssessmentReport {

    public String generate(AcademicClass academicClass) {

        return """
               Turma: %s
               Total de avaliações: %d
               """
               .formatted(academicClass.getName(),academicClass.getAssessments().size());
    }
}
