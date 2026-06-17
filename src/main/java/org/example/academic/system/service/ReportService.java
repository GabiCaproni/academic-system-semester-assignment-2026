/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.example.academic.system.service;

/**
 *
 * @author Gabi Caproni
 */
import java.util.List;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Assessment;

public class ReportService {

    public String generateSummary(
            List<AcademicClass> classes) {

        StringBuilder report
                = new StringBuilder();

        for (AcademicClass c : classes) {

            report.append("Turma: ")
                    .append(c.getName())
                    .append("\n");

            for (Assessment a
                    : c.getAssessments()) {

                report.append(a.getName())
                        .append(" | Peso: ")
                        .append(a.getWeight())
                        .append(" | Valor: ")
                        .append(a.getValue())
                        .append("\n");
            }

            report.append("\n");
        }

        return report.toString();
    }

    public String generateWeightReport(
            List<AcademicClass> classes) {

        StringBuilder report = new StringBuilder();

        for (AcademicClass c : classes) {

            report.append("Turma: ")
                    .append(c.getName())
                    .append("\n");

            double totalWeight = 0;

            for (Assessment a : c.getAssessments()) {

                report.append(a.getName())
                        .append(" - Peso: ")
                        .append(a.getWeight())
                        .append("\n");

                totalWeight += a.getWeight();
            }

            report.append("Peso total: ")
                    .append(totalWeight)
                    .append("\n\n");
        }

        return report.toString();
    }
}
