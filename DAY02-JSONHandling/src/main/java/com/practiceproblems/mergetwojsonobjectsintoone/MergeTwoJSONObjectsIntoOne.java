/*
Merge two JSON objects into one.
*/
package com.practiceproblems.mergetwojsonobjectsintoone;

import org.json.JSONObject;

public class MergeTwoJSONObjectsIntoOne {
    public static void main(String[] args) {
        // Create first JSON object
        JSONObject json1 = new JSONObject();
        json1.put("name", "Alice");
        json1.put("email", "alice@example.com");

        // Create second JSON object
        JSONObject json2 = new JSONObject();
        json2.put("city", "New York");
        json2.put("age", 25);

        // Merge json2 into json1
        for (String key : json2.keySet()) {
            json1.put(key, json2.get(key));
        }

        // Print merged JSON object
        System.out.println(json1.toString(4)); // Pretty print JSON with indentation
    }
}
