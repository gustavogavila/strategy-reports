package com.gus.strategyreports.reports;

import java.io.Writer;

public class ReportDataManager {
    private final ReportDataStrategy reportDataStrategy;

    public ReportDataManager(ReportDataStrategy reportDataStrategy) {
        this.reportDataStrategy = reportDataStrategy;
    }

    public void writeReport(Writer writer) {
        reportDataStrategy.writeToCsv(writer);
    }


}
