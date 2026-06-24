package org.example.academic.system.report;

import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Assessment;
import org.example.academic.system.model.Role;

public class AssessmentWeightReport {

    private final ReportLog log =
            new ReportLog();

    public String generate(
            AcademicClass academicClass,
            Role role) {

        log.log(
                "WEIGHT_REPORT",
                role);

        StringBuilder sb =
                new StringBuilder();

        double totalWeight = 0;

        for (Assessment a
                : academicClass.getAssessments()) {

            sb.append(a.getName())
              .append(" - ")
              .append(a.getWeight())
              .append("\n");

            totalWeight += a.getWeight();
        }

        sb.append("\nPeso total: ")
          .append(totalWeight);

        return sb.toString();
    }
}
