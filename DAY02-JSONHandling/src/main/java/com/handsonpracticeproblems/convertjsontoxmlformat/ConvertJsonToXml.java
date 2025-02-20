/*
 Convert JSON to XML format.
 */
package com.handsonpracticeproblems.convertjsontoxmlformat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;

public class ConvertJsonToXml {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            XmlMapper xmlMapper = new XmlMapper();

            // Read JSON from file
            JsonNode jsonNode = objectMapper.readTree(new File("src/main/resources/data.json"));

            // Convert JSON to XML
            String xml = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);

            // Print XML output
            System.out.println("Converted XML:\n" + xml);

            // Optionally, write to a file
            xmlMapper.writeValue(new File("output.xml"), jsonNode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
