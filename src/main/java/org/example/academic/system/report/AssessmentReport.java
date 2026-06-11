/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.academic.system.report;

/**
 *
 * @author Gabi Caproni
 */

import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Assessment;

public class AssessmentReport {

    public void generateSummary(AcademicClass academicClass) {

        System.out.println("\n===== ASSESSMENT SUMMARY =====");
        System.out.println("Class: " + academicClass.getName());

        for (Assessment assessment : academicClass.getAssessments()) {

            System.out.println(
                    assessment.getName()
                    + " | Weight: "
                    + assessment.getWeight()
                    + " | Value: "
                    + assessment.getValue());
        }
    }

    public void generateWeightReport(AcademicClass academicClass) {

        double totalWeight = 0;

        System.out.println("\n===== WEIGHT REPORT =====");

        for (Assessment assessment : academicClass.getAssessments()) {

            System.out.println(
                    assessment.getName()
                    + " -> "
                    + assessment.getWeight()
                    + "%");

            totalWeight += assessment.getWeight();
        }

        System.out.println("---------------------");
        System.out.println("Total weight: " + totalWeight + "%");
    }
}