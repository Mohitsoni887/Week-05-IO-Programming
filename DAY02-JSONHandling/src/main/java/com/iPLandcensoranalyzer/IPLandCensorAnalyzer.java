/*
Problem Statement: IPL and Censor Analyzer
Objective:
Develop a Java application that reads IPL match data from JSON and CSV files, processes the data based on defined censorship rules, and writes the sanitized data back to new files.
 */
package com.iPLandcensoranalyzer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
        import java.util.stream.Collectors;

public class IPLandCensorAnalyzer {

    // Convert CSV to JSON
    public static void convertCsvToJson(String inputCsvPath, String outputJsonPath) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(inputCsvPath));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        List<Map<String, String>> records = new ArrayList<>();

        for (CSVRecord record : csvParser) {
            Map<String, String> row = new HashMap<>();
            for (String header : record.toMap().keySet()) {
                row.put(header, record.get(header));
            }
            records.add(row);
        }
        csvParser.close();

        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputJsonPath), records);
    }

    public static void main(String[] args) {
        try {
            convertCsvToJson("src/main/resources/ipl_data.csv", "src/main/resources/ipl_data.json");
            System.out.println("CSV to JSON conversion completed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
