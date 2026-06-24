package org.example.academic.system.service;

public class ReportLogService {

    public void registerLog(
            String reportName,
            String role) {

        System.out.println(
                "[REPORT LOG] "
                + reportName
                + " | Role: "
                + role);
    }
}