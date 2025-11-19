package com.xgbt95json;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONReadXGBT95 {
    public static void main(String[] args) {
        try {
            InputStream is = JSONReadXGBT95.class.getClassLoader()
                    .getResourceAsStream("orarendXGBT95.json");

            if (is == null) {
                System.out.println("Nem található a JSON fájl a resources mappában!");
                return;
            }

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(new InputStreamReader(is, "UTF-8"));

            JSONObject root = (JSONObject) jsonObject.get("XGBT95_orarend");
            JSONArray lessons = (JSONArray) root.get("ora");

            System.out.println("XGBT95 Órarend 2025 ősz\n");

            for (Object o : lessons) {
                JSONObject lesson = (JSONObject) o;

                JSONObject time = (JSONObject) lesson.get("idopont");

                System.out.println("-------------------------------");
                System.out.println("ID: " + lesson.get("id"));
                System.out.println("Típus: " + lesson.get("tipus"));
                System.out.println("Tárgy: " + lesson.get("targy"));
                System.out.println("Nap: " + time.get("nap"));
                System.out.println("Idő: " + time.get("tol") + " - " + time.get("ig"));
                System.out.println("Helyszín: " + lesson.get("helyszin"));
                System.out.println("Oktató: " + lesson.get("oktato"));
                System.out.println("Szak: " + lesson.get("szak"));
                System.out.println("-------------------------------\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}