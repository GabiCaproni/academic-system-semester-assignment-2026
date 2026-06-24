package org.example.academic.system.report;

import java.time.LocalDateTime;
import org.example.academic.system.model.Role;

public class ReportLog {

    public void log(
            String reportType,
            Role role) {

        System.out.println(
                "[LOG] Relatório gerado: "
                + reportType
                + " | Perfil: "
                + role
                + " | "
                + LocalDateTime.now());
    }
}