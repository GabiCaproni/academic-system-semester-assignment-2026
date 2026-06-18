/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Gabi Caproni
 */

import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.controller.AcademicSystemController;
import org.example.academic.system.model.Assessment;
import org.example.academic.system.model.Exam;
import org.example.academic.system.repository.AcademicClassRepository;
import org.example.academic.system.service.AcademicClassService;
import org.example.academic.system.service.AssessmentService;
import org.example.academic.system.service.ReportService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AcademicSystemControllerTest {

    @Test
    void shouldRegisterClass() {

        AcademicClassRepository repository =
                new AcademicClassRepository();

        AcademicClassService classService =
                new AcademicClassService(
                        repository);

        AssessmentService assessmentService =
                new AssessmentService(
                        repository);

        ReportService reportService =
                new ReportService();

        AcademicSystemController controller =
                new AcademicSystemController(
                        classService,
                        assessmentService,
                        reportService);

        AcademicClass academicClass =
                new AcademicClass(
                        "POO",
                        "Programacao Orientada a Objetos");

        controller.registerClass(
                academicClass);

        assertEquals(
                1,
                controller.getClasses().size());
    }

    @Test
    void shouldRegisterAssessment() {

        AcademicClassRepository repository =
                new AcademicClassRepository();

        AcademicClassService classService =
                new AcademicClassService(
                        repository);

        AssessmentService assessmentService =
                new AssessmentService(
                        repository);

        ReportService reportService =
                new ReportService();

        AcademicSystemController controller =
                new AcademicSystemController(
                        classService,
                        assessmentService,
                        reportService);

        AcademicClass academicClass =
                new AcademicClass(
                        "POO",
                        "Programacao");

        controller.registerClass(
                academicClass);

        Assessment assessment =
                new Exam(
                        "P1",
                        10,
                        100);

        controller.registerAssessment(
                academicClass,
                assessment);

        assertEquals(
                1,
                academicClass.getAssessments().size());
    }

    @Test
    void shouldGenerateSummaryReport() {

        AcademicClassRepository repository =
                new AcademicClassRepository();

        AcademicClassService classService =
                new AcademicClassService(
                        repository);

        AssessmentService assessmentService =
                new AssessmentService(
                        repository);

        ReportService reportService =
                new ReportService();

        AcademicSystemController controller =
                new AcademicSystemController(
                        classService,
                        assessmentService,
                        reportService);

        String report =
                controller.generateSummary();

        assertNotNull(report);
    }

    @Test
    void shouldGenerateWeightReport() {

        AcademicClassRepository repository =
                new AcademicClassRepository();

        AcademicClassService classService =
                new AcademicClassService(
                        repository);

        AssessmentService assessmentService =
                new AssessmentService(
                        repository);

        ReportService reportService =
                new ReportService();

        AcademicSystemController controller =
                new AcademicSystemController(
                        classService,
                        assessmentService,
                        reportService);

        String report =
                controller.generateWeightReport();

        assertNotNull(report);
    }
}