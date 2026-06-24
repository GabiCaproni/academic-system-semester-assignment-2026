package org.example.academic.system.service;

import java.util.List;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Assessment;
import org.example.academic.system.security.SessionManager;

public class ReportService {

    private SessionManager sessionManager;
    private ReportLogService logService;

    public ReportService(
            SessionManager sessionManager) {

        this.sessionManager = sessionManager;
        this.logService = new ReportLogService();
    }

    public String generateSummary(
            List<AcademicClass> classes) {

        if (sessionManager != null
                && sessionManager.isAuthenticated()) {

            logService.registerLog(
                    "SUMMARY_REPORT",
                    sessionManager
                            .getLoggedUser()
                            .getRole()
                            .toString());
        }

        StringBuilder report =
                new StringBuilder();

        for (AcademicClass c : classes) {

            report.append("Turma: ")
                    .append(c.getCode())
                    .append(" - ")
                    .append(c.getName())
                    .append("\n");

            for (Assessment a
                    : c.getAssessments()) {

                report.append(a.getClass()
                               .getSimpleName())
                        .append(" | ")
                        .append(a.getName())
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

        if (sessionManager != null
                && sessionManager.isAuthenticated()) {

            logService.registerLog(
                    "WEIGHT_REPORT",
                    sessionManager
                            .getLoggedUser()
                            .getRole()
                            .toString());
        }

        StringBuilder report =
                new StringBuilder();

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

    public String generatePersistenceConfigurationReport() {

        if (sessionManager != null
                && sessionManager.isAuthenticated()) {

            logService.registerLog(
                    "PERSISTENCE_CONFIGURATION_REPORT",
                    sessionManager
                            .getLoggedUser()
                            .getRole()
                            .toString());
        }

        return "Persistence Type: MEMORY";
    }
}