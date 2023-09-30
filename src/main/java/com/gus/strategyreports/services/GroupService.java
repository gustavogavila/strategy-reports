package com.gus.strategyreports.services;

import com.gus.strategyreports.reports.types.GroupsReport;
import com.gus.strategyreports.reports.ReportDataManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Writer;

@Service
@RequiredArgsConstructor
@Slf4j
public class GroupService {
    private final GroupsReport groupsReport;

    public void downloadReport(Writer writer) {
        ReportDataManager reportDataManager = new ReportDataManager(groupsReport);
        reportDataManager.writeReport(writer);
    }
}
