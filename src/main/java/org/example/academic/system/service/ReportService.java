/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.example.academic.system.service;

/**
 *
 * @author Gabi Caproni
 */

import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Assessment;

public class ReportService {

    public void generateAssessmentSummary(AcademicClass academicClass) {

        System.out.println("\n===== ASSESSMENT SUMMARY =====");
        System.out.println("Class: " + academicClass.getName());
        System.out.println("Code: " + academicClass.getCode());

        int totalAssessments = academicClass.getAssessments().size();

        for (Assessment assessment : academicClass.getAssessments()) {
            System.out.println("---------------------------");
            System.out.println("Name: " + assessment.getName());
            System.out.println("Weight: " + assessment.getWeight());
            System.out.println("Value: " + assessment.getValue());
        }

        System.out.println("---------------------------");
        System.out.println("Total assessments: " + totalAssessments);
    }

    public void generateAssessmentWeightReport(AcademicClass academicClass) {

        System.out.println("\n===== ASSESSMENT WEIGHT REPORT =====");
        System.out.println("Class: " + academicClass.getName());

        double totalWeight = 0;

        for (Assessment assessment : academicClass.getAssessments()) {

            System.out.println(
                    assessment.getName()
                    + " -> "
                    + assessment.getWeight()
                    + "%"
            );

            totalWeight += assessment.getWeight();
        }

        System.out.println("---------------------------");
        System.out.println("Total weight: " + totalWeight + "%");

        if (totalWeight == 100) {
            System.out.println("Weights are valid.");
        } else {
            System.out.println("WARNING: weights do not sum to 100%.");
        }
    }
}