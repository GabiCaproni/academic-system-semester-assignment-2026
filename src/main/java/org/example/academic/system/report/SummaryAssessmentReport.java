package org.example.academic.system.report;

import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Role;

public class SummaryAssessmentReport {

    private final ReportLog log =
            new ReportLog();

    public String generate(
            AcademicClass academicClass,
            Role role) {

        log.log(
                "SUMMARY_REPORT",
                role);

        return """
               Turma: %s
               Total de avaliações: %d
               """
               .formatted(
                       academicClass.getName(),
                       academicClass.getAssessments().size());
    }
}