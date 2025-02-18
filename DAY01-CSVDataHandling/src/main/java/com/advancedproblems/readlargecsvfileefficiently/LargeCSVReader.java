/*
Read Large CSV File Efficiently
Given a large CSV file (500MB+), implement a memory-efficient way to read it in chunks.
Process only 100 lines at a time and display the count of records processed.
*/
package com.advancedproblems.readlargecsvfileefficiently;
import java.io.*;

public class LargeCSVReader {
    public static void main(String[] args) {
        String filePath = "src/main/resources/large_data.csv"; // Large CSV file path
        int batchSize = 100; // Number of lines to read in each batch
        int totalRecords = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;
            int batchCount = 0;

            while ((line = br.readLine()) != null) {
                // Skip the header row
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                // Process the record (for now, just counting it)
                totalRecords++;
                batchCount++;

                // If batch limit is reached, print and reset counter
                if (batchCount == batchSize) {
                    System.out.println("Processed " + totalRecords + " records...");
                    batchCount = 0; // Reset batch counter
                }
            }

            // Print final count if remaining records exist
            if (batchCount > 0) {
                System.out.println("Processed " + totalRecords + " records...");
            }

            System.out.println("Finished processing. Total records: " + totalRecords);

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}

