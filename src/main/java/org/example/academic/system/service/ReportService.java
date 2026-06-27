package org.example.academic.system.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Assessment;
import org.example.academic.system.security.SessionManager;

public class ReportService {

private final SessionManager sessionManager;
private final ReportLogService logService;

public ReportService(
        SessionManager sessionManager) {

    this.sessionManager = sessionManager;
    this.logService = new ReportLogService();
}

public String generateSummary(
        List<AcademicClass> classes) {

    registerAccess("SUMMARY_REPORT");

    StringBuilder report =
            new StringBuilder();

    report.append("===== RELATÓRIO RESUMIDO =====\n\n");

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

    registerAccess("WEIGHT_REPORT");

    StringBuilder report =
            new StringBuilder();

    report.append("===== RELATÓRIO DE PESOS =====\n\n");

    for (AcademicClass c : classes) {

        report.append("Turma: ")
                .append(c.getName())
                .append("\n");

        double totalWeight = 0;

        for (Assessment a
                : c.getAssessments()) {

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

    registerAccess(
            "PERSISTENCE_CONFIGURATION_REPORT");

    StringBuilder report =
            new StringBuilder();

    File jsonFile =
            new File("academic_data.json");

    File xmlFile =
            new File("academic_data.xml");

    report.append(
            "===== RELATÓRIO DE PERSISTÊNCIA =====\n\n");

    report.append(
            "Configuração atual: MEMORY\n\n");

    report.append(
            "ARQUIVO JSON\n");

    report.append("Existe: ")
            .append(jsonFile.exists())
            .append("\n");

    if (jsonFile.exists()) {

        report.append("Tamanho: ")
                .append(jsonFile.length())
                .append(" bytes\n");

        report.append(
                "Última modificação: ")
                .append(
                        new Date(
                                jsonFile.lastModified()))
                .append("\n");
    }

    report.append("\n");

    report.append(
            "ARQUIVO XML\n");

    report.append("Existe: ")
            .append(xmlFile.exists())
            .append("\n");

    if (xmlFile.exists()) {

        report.append("Tamanho: ")
                .append(xmlFile.length())
                .append(" bytes\n");

        report.append(
                "Última modificação: ")
                .append(
                        new Date(
                                xmlFile.lastModified()))
                .append("\n");
    }

    return report.toString();
}

private void registerAccess(
        String reportType) {

    if (sessionManager != null
            && sessionManager.isAuthenticated()) {

        logService.registerLog(
                reportType,
                sessionManager
                        .getLoggedUser()
                        .getRole()
                        .toString());
    }
}
}
