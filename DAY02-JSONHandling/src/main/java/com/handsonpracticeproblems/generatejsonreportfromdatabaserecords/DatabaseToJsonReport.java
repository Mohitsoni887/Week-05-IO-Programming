/*
 Generate a JSON report from database records.
 */
package com.handsonpracticeproblems.generatejsonreportfromdatabaserecords;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseToJsonReport {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb"; // Change DB details
        String user = "root";
        String password = "password";

        String query = "SELECT id, name, age, email FROM users"; // Modify as per your table

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            List<Map<String, Object>> records = new ArrayList<>();

            // Process ResultSet
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("id", rs.getInt("id"));
                row.put("name", rs.getString("name"));
                row.put("age", rs.getInt("age"));
                row.put("email", rs.getString("email"));
                records.add(row);
            }

            // Convert List to JSON
            ObjectMapper jsonMapper = new ObjectMapper();
            String jsonReport = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(records);

            // Print JSON output
            System.out.println("Generated JSON Report:\n" + jsonReport);

            // Write JSON to file
            jsonMapper.writeValue(new File("report.json"), records);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
