package com.gus.strategyreports.reports.types;

import com.gus.strategyreports.domain.User;
import com.gus.strategyreports.reports.ReportDataStrategy;
import com.gus.strategyreports.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class UsersReport implements ReportDataStrategy {
    private final UserRepository userRepository;

    @Override
    public void writeToCsv(Writer writer) {
        List<User> allUsers = userRepository.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("ID", "Fullname", "Username", "CreatedAt");
            for (User user : allUsers) {
                csvPrinter.printRecord(user.getId(),
                        user.getFullname(),
                        user.getUsername(),
                        user.getCreatedAt().toString());
            }
        } catch (IOException e) {
            log.error("Error while writing CSV file of UsersReport", e);
        }
    }
}
