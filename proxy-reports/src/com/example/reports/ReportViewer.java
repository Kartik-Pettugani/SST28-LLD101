package com.example.reports;

public class ReportViewer {
    // Dependency inversion: depend on the interface, not the implementation
    public void open(Report report, User user) {
        report.display(user);
    }
}