/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.academic.system.report;

import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Assessment;

/**
 *
 * @author Gabi Caproni
 */
public class AssessmentWeightReport {

    public String generate(AcademicClass academicClass) {

        StringBuilder sb = new StringBuilder();

        double totalWeight = 0;

        for (Assessment a : academicClass.getAssessments()) {

            sb.append(a.getName())
              .append(" - ")
              .append(a.getWeight())
              .append("\n");

            totalWeight += a.getWeight();
        }

        sb.append("\nPeso total: ")
          .append(totalWeight);

        return sb.toString();
    }
}