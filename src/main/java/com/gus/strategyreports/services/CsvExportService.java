package com.gus.strategyreports.services;

import com.gus.strategyreports.domain.Group;
import com.gus.strategyreports.domain.User;
import com.gus.strategyreports.repositories.GroupRepository;
import com.gus.strategyreports.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CsvExportService {

    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    public void writeUsersToCsv(Writer writer) {
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
            log.error("Error while writing CSV file", e);
        }
    }

    public void writeGroupsToCsv(Writer writer) {
        List<Group> allGroups = groupRepository.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("ID", "Name", "CreatedAt");
            for (Group group : allGroups) {
                csvPrinter.printRecord(group.getId(),
                        group.getName(),
                        group.getCreatedAt().toString());
            }
        } catch (IOException e) {
            log.error("Error while writing CSV file", e);
        }
    }
}
