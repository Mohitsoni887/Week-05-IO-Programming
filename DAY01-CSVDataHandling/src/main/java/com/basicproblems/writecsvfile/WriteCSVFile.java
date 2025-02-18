/*
Write Data to a CSV File
Create a CSV file with employee details (ID, Name, Department, Salary).
Write at least 5 records to the file.
*/
package com.basicproblems.writecsvfile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteCSVFile {
    public static void main(String[] args) {
        String filePath = "src/main/resources/employees.csv"; // File where data will be written

        // Employee details (ID, Name, Department, Salary)
        String[] employees = {
                "101,John Doe,Engineering,75000",
                "102,Jane Smith,HR,65000",
                "103,David Brown,Finance,72000",
                "104,Emily White,Marketing,68000",
                "105,Michael Green,IT,80000"
        };

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            // Write CSV Header
            bw.write("ID,Name,Department,Salary");
            bw.newLine(); // Move to next line

            // Write Employee Records
            for (String employee : employees) {
                bw.write(employee);
                bw.newLine();
            }

            System.out.println("CSV file written successfully: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}

