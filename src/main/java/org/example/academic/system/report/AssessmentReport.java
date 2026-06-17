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

    public void generateWeightReport(AcademicClass academicClass) {

        double totalWeight = 0;

        System.out.println("\n===== RELATÓRIO DE PESOS =====\n");
        System.out.println("Turma: " + academicClass.getName());
        System.out.println();

        System.out.printf("%-20s %s%n", "Avaliação", "Peso");
        System.out.println("--------------------------------");

        for (Assessment assessment : academicClass.getAssessments()) {

            System.out.printf("%-20s %.0f%%%n",assessment.getName(),assessment.getWeight());

            totalWeight += assessment.getWeight();
        }

        System.out.println("--------------------------------");

        System.out.printf("%-20s %.0f%%%n", "Total", totalWeight);
    }

    public void generateSummary(AcademicClass academicClass) {

        System.out.println("\n===== RELATÓRIO RESUMIDO =====\n");

        System.out.println("Turma: " + academicClass.getName());
        System.out.println();

        System.out.printf("%-20s %-10s %-10s%n", "Avaliação", "Peso", "Valor");

        System.out.println("------------------------------------------");

        for (Assessment assessment : academicClass.getAssessments()) {

            System.out.printf("%-20s %-10.0f %-10.0f%n",assessment.getName(),assessment.getWeight(),assessment.getValue());
        }
    }
}