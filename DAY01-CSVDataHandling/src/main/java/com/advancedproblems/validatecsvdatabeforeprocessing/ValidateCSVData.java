/*
Validate CSV Data Before Processing
Ensure that the "Email" column follows a valid email format using regex.
Ensure that "Phone Numbers" contain exactly 10 digits.
Print any invalid rows with an error message.
*/

package com.advancedproblems.validatecsvdatabeforeprocessing;

import java.io.*;
import java.util.regex.*;

public class ValidateCSVData {
    public static void main(String[] args) {
        String filePath = "src/main/resources/contacts.csv"; // Input CSV file

        // Regular Expressions
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        String phoneRegex = "^[0-9]{10}$";

        // Compile Patterns
        Pattern emailPattern = Pattern.compile(emailRegex);
        Pattern phonePattern = Pattern.compile(phoneRegex);

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                // Print header as-is
                if (isHeader) {
                    System.out.println("Valid Records:");
                    System.out.println(line);
                    isHeader = false;
                    continue;
                }

                // Ensure correct number of columns (Assuming columns: ID, Name, Email, Phone)
                if (values.length != 4) {
                    System.out.println("Invalid Row (Incorrect Columns): " + line);
                    continue;
                }

                String email = values[2].trim();
                String phone = values[3].trim();

                // Validate Email and Phone
                boolean isValidEmail = emailPattern.matcher(email).matches();
                boolean isValidPhone = phonePattern.matcher(phone).matches();

                if (!isValidEmail || !isValidPhone) {
                    System.out.println("Invalid Row (Format Error): " + line);
                    if (!isValidEmail) System.out.println("  -> Invalid Email: " + email);
                    if (!isValidPhone) System.out.println("  -> Invalid Phone: " + phone);
                } else {
                    // Print valid row
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}
