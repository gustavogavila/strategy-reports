package com.gus.strategyreports.controllers;

import com.gus.strategyreports.dtos.UserRequest;
import com.gus.strategyreports.dtos.UserResponse;
import com.gus.strategyreports.services.CsvExportService;
import com.gus.strategyreports.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final CsvExportService csvExportService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.ok(userService.create(userRequest));
    }

    @GetMapping("csv")
    public void getAllUsersInCSV(HttpServletResponse servletResponse) throws IOException {
        buildHeaderResponse(servletResponse);
        csvExportService.writeUsersToCsv(servletResponse.getWriter());
    }

    private static void buildHeaderResponse(HttpServletResponse servletResponse) {
        servletResponse.setContentType("text/csv");
        String timestamp = OffsetDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String filename = String.format("%s__all_users_report.csv", timestamp);
        servletResponse.addHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", filename));
    }
}
