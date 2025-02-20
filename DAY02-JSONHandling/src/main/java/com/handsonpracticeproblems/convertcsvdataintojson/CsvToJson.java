/*
 Convert CSV data into JSON.
 */
package com.handsonpracticeproblems.convertcsvdataintojson;

import com.opencsv.CSVReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
// Convert CSV data into JSON
public class CsvToJson {
    public static void main(String[] args) {
        try {
            // Initialize CSVReader to read the CSV file
            CSVReader reader = new CSVReader(new FileReader("src/main/resources/data.csv"));

            // Read all rows from the CSV into a list of strings
            List<String[]> rows = reader.readAll();

            // Extract the header (the first row) for JSON keys
            String[] header = rows.get(0);

            // Initialize a list to hold the JSON objects
            List<Map<String, String>> jsonList = new ArrayList<>();

            // Iterate over the remaining rows (skip header)
            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);
                Map<String, String> rowMap = new HashMap<>();

                // Map each CSV field to the corresponding header field
                for (int j = 0; j < header.length; j++) {
                    rowMap.put(header[j], row[j]);
                }

                // Add the map to the JSON list
                jsonList.add(rowMap);
            }

            // Convert the list of maps to a JSON string
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(jsonList);

            // Print the JSON output
            System.out.println(json);

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }
}
