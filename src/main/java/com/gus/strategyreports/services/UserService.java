package com.gus.strategyreports.services;

import com.gus.strategyreports.domain.User;
import com.gus.strategyreports.dtos.UserRequest;
import com.gus.strategyreports.dtos.UserResponse;
import com.gus.strategyreports.reports.ReportDataManager;
import com.gus.strategyreports.reports.types.UsersReport;
import com.gus.strategyreports.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Writer;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UsersReport usersReport;

    public UserResponse create(UserRequest userRequest) {
        User user = new User();
        user.setFullname(userRequest.getFullname());
        user.setUsername(userRequest.getUsername());
        user.setCreatedAt(userRequest.getCreatedAt());
        User savedUser = userRepository.save(user);
        return new UserResponse(savedUser.getId(),
                savedUser.getFullname(),
                savedUser.getUsername(),
                savedUser.getCreatedAt());
    }

    public void downloadReport(Writer writer) {
        ReportDataManager reportDataManager = new ReportDataManager(usersReport);
        reportDataManager.writeReport(writer);
    }
}
