package org.example.academic.system;

import org.example.academic.system.controller.AcademicSystemController;
import org.example.academic.system.controller.AuthenticationController;
import org.example.academic.system.repository.AcademicClassRepository;
import org.example.academic.system.repository.UserRepository;
import org.example.academic.system.security.SessionManager;
import org.example.academic.system.service.AcademicClassService;
import org.example.academic.system.service.AssessmentService;
import org.example.academic.system.service.ReportService;
import org.example.academic.system.view.AcademicSystemView;

public class Main {

    public static void main(String[] args) {

        // Repositórios
        UserRepository userRepository =
                new UserRepository();

        AcademicClassRepository classRepository =
                new AcademicClassRepository();

        // Segurança
        SessionManager sessionManager =
                new SessionManager();

        // Serviços
        AcademicClassService classService =
                new AcademicClassService(
                        classRepository);

        AssessmentService assessmentService =
                new AssessmentService(
                        classRepository);

        ReportService reportService =
                new ReportService();

        // Controllers
        AuthenticationController authController =
                new AuthenticationController(
                        userRepository,
                        sessionManager);

        AcademicSystemController academicController =
                new AcademicSystemController(
                        classService,
                        assessmentService,
                        reportService);

        // Interface
        AcademicSystemView view =
                new AcademicSystemView(
                        authController,
                        academicController);

        // Inicia o sistema
        view.start();
    }
}