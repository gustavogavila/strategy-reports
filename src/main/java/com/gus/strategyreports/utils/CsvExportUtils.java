package com.gus.strategyreports.utils;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class CsvExportUtils {

    public static void buildHeaderResponse(HttpServletResponse servletResponse, String reportName) {
        servletResponse.setContentType("text/csv");
        String timestamp = OffsetDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String filename = String.format("%s__%s.csv", timestamp, reportName);
        servletResponse.addHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", filename));
    }
}
