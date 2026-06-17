/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.example.academic.system.controller;

/**
 *
 * @author Gabi Caproni
 */

import java.util.List;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Assessment;
import org.example.academic.system.service.AcademicClassService;
import org.example.academic.system.service.AssessmentService;
import org.example.academic.system.service.ReportService;

public class AcademicSystemController {

    private final AcademicClassService classService;
    private final AssessmentService assessmentService;
    private final ReportService reportService;

    public AcademicSystemController(AcademicClassService classService, AssessmentService assessmentService, ReportService reportService) {

        this.classService = classService;
        this.assessmentService = assessmentService;
        this.reportService = reportService;
    }

    public void registerClass(
            AcademicClass academicClass) {

        classService.registerClass(academicClass);
    }

    public void registerAssessment(
            AcademicClass academicClass,
            Assessment assessment) {

        assessmentService.registerAssessment(
                academicClass,
                assessment);
    }

    public List<AcademicClass> getClasses() {

        return classService.getClasses();
    }

    public String generateSummary() {

        return reportService.generateSummary(
                classService.getClasses());
    }

    public String generateWeightReport() {

        return reportService.generateWeightReport(
                classService.getClasses());
    }
}
