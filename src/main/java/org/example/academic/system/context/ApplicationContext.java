package org.example.academic.system.context;

import org.example.academic.system.controller.AcademicSystemController;
import org.example.academic.system.controller.AuthenticationController;
import org.example.academic.system.repository.AcademicClassRepository;
import org.example.academic.system.repository.UserRepository;
import org.example.academic.system.security.SessionManager;
import org.example.academic.system.service.AcademicClassService;
import org.example.academic.system.service.AssessmentService;
import org.example.academic.system.service.ReportService;

public class ApplicationContext {

    private static ApplicationContext instance;

    private final AcademicClassRepository classRepository;
    private final SessionManager sessionManager;
    private final UserRepository userRepository;
    private final AcademicClassService classService;
    private final AssessmentService assessmentService;
    private final ReportService reportService;
    private final AcademicSystemController academicController;
    private final AuthenticationController authController;

    private ApplicationContext() {
        this.classRepository = new AcademicClassRepository();
        this.sessionManager = new SessionManager();
        this.userRepository = new UserRepository();
        
        this.classService = new AcademicClassService(classRepository);
        this.assessmentService = new AssessmentService(classRepository);
        this.reportService = new ReportService(sessionManager);
        
        this.academicController = new AcademicSystemController(
            classService,
            assessmentService,
            reportService
        );
        
        this.authController = new AuthenticationController(
            userRepository,
            sessionManager
        );
    }

    public static ApplicationContext getInstance() {
        if (instance == null) {
            instance = new ApplicationContext();
        }
        return instance;
    }

    public AcademicSystemController getAcademicController() {
        return academicController;
    }

    public AuthenticationController getAuthController() {
        return authController;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }
}