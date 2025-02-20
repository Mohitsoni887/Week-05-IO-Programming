/*
 Convert a list of Java objects into a JSON array.
*/
package com.practiceproblems.convertjavaobjectsintojsonarray;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;

class Car {
    private String brand;
    private String model;
    private int year;

    // Constructor
    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    // Getters (required for Jackson)
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }
}

public class ListToJsonArrayExample {
    public static void main(String[] args) {
        try {
            // Create a list of Car objects
            List<Car> carList = Arrays.asList(
                    new Car("Toyota", "Camry", 2022),
                    new Car("Honda", "Civic", 2021),
                    new Car("Ford", "Mustang", 2023)
            );

            // Convert list to JSON array
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonArray = objectMapper.writeValueAsString(carList);

            // Print JSON array
            System.out.println(jsonArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

