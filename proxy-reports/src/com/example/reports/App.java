package com.example.reports;

public class App {

    public static void main(String[] args) {
        User student = new User("Jasleen", "STUDENT");
        User faculty = new User("Prof. Noor", "FACULTY");
        User admin = new User("Kshitij", "ADMIN");

        // Use the Proxy instead of the concrete ReportFile
        Report publicReport = new ReportProxy("R-101", "Orientation Plan", "PUBLIC");
        Report facultyReport = new ReportProxy("R-202", "Midterm Review", "FACULTY");
        Report adminReport = new ReportProxy("R-303", "Budget Audit", "ADMIN");

        ReportViewer viewer = new ReportViewer();

        System.out.println("=== CampusVault Demo ===");

        // Should work (Public)
        viewer.open(publicReport, student);
        System.out.println();

        // Should be DENIED (Student trying to access Faculty)
        viewer.open(facultyReport, student);
        System.out.println();

        // Should work (Faculty)
        viewer.open(facultyReport, faculty);
        System.out.println();

        // Should work (Admin)
        viewer.open(adminReport, admin);
        System.out.println();

        // Should work AND NOT trigger "[disk] loading..." again (Caching)
        viewer.open(adminReport, admin);
    }
}