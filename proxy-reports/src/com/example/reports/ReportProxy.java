package com.example.reports;

public class ReportProxy implements Report {

    private final String reportId;
    private final String title;
    private final String classification;
    private final AccessControl accessControl = new AccessControl();
    
    // Cache the real report to ensure lazy loading only happens once
    private RealReport realReport;

    public ReportProxy(String reportId, String title, String classification) {
        this.reportId = reportId;
        this.title = title;
        this.classification = classification;
    }

    @Override
    public void display(User user) {
        // 1. Access Control Check
        if (!accessControl.canAccess(user, classification)) {
            System.out.println("ACCESS DENIED: User " + user.getName() 
                + " cannot view " + classification + " reports.");
            return;
        }

        // 2. Lazy Loading & Caching
        if (realReport == null) {
            realReport = new RealReport(reportId, title, classification);
        }

        // 3. Delegation
        realReport.display(user);
    }
}