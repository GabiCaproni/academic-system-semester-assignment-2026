import java.util.List;

import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.PracticalAssignment;
import org.example.academic.system.model.Role;
import org.example.academic.system.model.User;
import org.example.academic.system.security.SessionManager;
import org.example.academic.system.service.ReportService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReportServiceTest {

    private ReportService createReportService() {

        SessionManager sessionManager =
                new SessionManager();

        User user =
                new User(
                        "admin",
                        "123",
                        Role.ADMIN);

        sessionManager.login(user);

        return new ReportService(
                sessionManager);
    }

    @Test
    void shouldGenerateSummaryReport() {

        AcademicClass academicClass =
                new AcademicClass(
                        "POO001",
                        "Programação Orientada a Objetos");

        academicClass.addAssessment(
                new PracticalAssignment(
                        "TP1",
                        30,
                        100,
                        "Java"));

        ReportService service =
                createReportService();

        String report =
                service.generateSummary(
                        List.of(academicClass));

        assertTrue(
                report.contains(
                        "POO001"));

        assertTrue(
                report.contains(
                        "Programação Orientada a Objetos"));

        assertTrue(
                report.contains(
                        "TP1"));
    }

    @Test
    void shouldGenerateWeightReport() {

        AcademicClass academicClass =
                new AcademicClass(
                        "POO001",
                        "Programação Orientada a Objetos");

        academicClass.addAssessment(
                new PracticalAssignment(
                        "TP1",
                        30,
                        100,
                        "Java"));

        ReportService service =
                createReportService();

        String report =
                service.generateWeightReport(
                        List.of(academicClass));

        assertTrue(
                report.contains(
                        "Peso"));

        assertTrue(
                report.contains(
                        "30"));
    }

    @Test
    void shouldGeneratePersistenceConfigurationReport() {

        ReportService service =
                createReportService();

        String report =
                service.generatePersistenceConfigurationReport();

        assertNotNull(
                report);

        assertTrue(
                report.contains(
                        "Persistence"));
    }
}