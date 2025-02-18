/*
 Detect Duplicates in a CSV File
Read a CSV file and detect duplicate entries based on the ID column.
Print all duplicate records.
*/
package com.advancedproblems.detectduplicatesinacsvfile;

import java.io.*;
import java.util.*;

public class DetectDuplicateIDs {
    public static void main(String[] args) {
        String filePath = "src/main/resources/data.csv"; // Path to the CSV file
        Set<String> uniqueIds = new HashSet<>();
        List<String> duplicateRecords = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false; // Skip header row
                    continue;
                }

                // Split CSV line by commas
                String[] values = line.split(",");
                if (values.length < 1) continue; // Skip malformed lines

                String id = values[0].trim(); // Extract ID column

                // Check for duplicate ID
                if (!uniqueIds.add(id)) {
                    duplicateRecords.add(line);
                }
            }

            // Print results
            if (duplicateRecords.isEmpty()) {
                System.out.println("No duplicate IDs found.");
            } else {
                System.out.println("Duplicate Records Found:");
                for (String record : duplicateRecords) {
                    System.out.println(record);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
