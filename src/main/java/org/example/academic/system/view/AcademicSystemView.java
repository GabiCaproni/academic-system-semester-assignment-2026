/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.academic.system.view;

import java.util.Scanner;

/**
 *
 * @author Gabi Caproni
 */
import org.example.academic.system.controller.AcademicSystemController;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Exam;
import org.example.academic.system.report.AssessmentReport;

public class AcademicSystemView {

    private AcademicSystemController controller;
    private Scanner scanner;

    public AcademicSystemView(AcademicSystemController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void start() {

        AcademicClass turma = new AcademicClass("CC01", "POO");

        controller.registerClass(turma);

        int option;

        do {

            System.out.println("\n=== ACADEMIC SYSTEM ===");
            System.out.println("1 - Register Exam");
            System.out.println("2 - Assessment Summary");
            System.out.println("3 - Weight Report");
            System.out.println("0 - Exit");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {

                case 1:
                    registerExam(turma);
                    break;

                case 2:
                    new AssessmentReport().generateSummary(turma);
                    break;

                case 3:
                    new AssessmentReport().generateWeightReport(turma);
                    break;

                case 0:
                    System.out.println("Finishing...");
                    break;

                default:
                    System.out.println("Invalid option.");
            }

        } while (option != 0);
    }

    private void registerExam(AcademicClass turma) {

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Weight: ");
        double weight = scanner.nextDouble();

        System.out.print("Value: ");
        double value = scanner.nextDouble();

        scanner.nextLine();

        controller.registerAssessment(
                turma,
                new Exam(name, weight, value)
        );

        System.out.println("Assessment registered!");
    }
}