package com.gus.strategyreports.controllers;

import com.gus.strategyreports.services.GroupService;
import com.gus.strategyreports.utils.CsvExportUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping("csv")
    public void getAllGroupsInCSV(HttpServletResponse servletResponse) throws IOException {
        CsvExportUtils.buildHeaderResponse(servletResponse, "all_groups_report");
        groupService.downloadReport(servletResponse.getWriter());
    }
}
