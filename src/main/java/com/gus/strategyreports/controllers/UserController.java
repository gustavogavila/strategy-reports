package com.gus.strategyreports.controllers;

import com.gus.strategyreports.dtos.UserRequest;
import com.gus.strategyreports.dtos.UserResponse;
import com.gus.strategyreports.services.UserService;
import com.gus.strategyreports.utils.CsvExportUtils;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.ok(userService.create(userRequest));
    }

    @GetMapping("csv")
    public void getAllUsersInCSV(HttpServletResponse servletResponse) throws IOException {
        CsvExportUtils.buildHeaderResponse(servletResponse, "all_users_report");
        userService.downloadReport(servletResponse.getWriter());
    }
}
