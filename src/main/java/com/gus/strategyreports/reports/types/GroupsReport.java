package com.gus.strategyreports.reports.types;

import com.gus.strategyreports.domain.Group;
import com.gus.strategyreports.reports.ReportDataStrategy;
import com.gus.strategyreports.repositories.GroupRepository;
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
public class GroupsReport implements ReportDataStrategy {

    private final GroupRepository groupRepository;

    @Override
    public void writeToCsv(Writer writer) {
        List<Group> allGroups = groupRepository.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("ID", "Name", "CreatedAt");
            for (Group group : allGroups) {
                csvPrinter.printRecord(group.getId(),
                        group.getName(),
                        group.getCreatedAt().toString());
            }
        } catch (IOException e) {
            log.error("Error while writing CSV file of GroupsReport", e);
        }
    }
}
