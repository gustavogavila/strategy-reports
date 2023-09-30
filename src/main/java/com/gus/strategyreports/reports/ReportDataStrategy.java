package com.gus.strategyreports.reports;

import java.io.Writer;

public interface ReportDataStrategy {
    void writeToCsv(Writer writer);
}
