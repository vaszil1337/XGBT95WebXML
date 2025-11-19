package com.xgbt95json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;

public class JSONWriteXGBT95 {

    public static void main(String[] args) {
        try {
            InputStream is = JSONWriteXGBT95.class.getClassLoader()
                    .getResourceAsStream("orarendXGBT95.json");

            if (is == null) {
                System.out.println("A 'orarendXGBT95.json' nem található a resources mappában!");
                return;
            }

            JSONParser parser = new JSONParser();
            JSONObject inputJson = (JSONObject) parser.parse(new InputStreamReader(is, "UTF-8"));

            JSONObject root = (JSONObject) inputJson.get("XGBT95_orarend");
            JSONArray lessons = (JSONArray) root.get("ora");

            System.out.println("XGBT95 Órarend 2025 ősz\n");

            JSONObject outputRoot = new JSONObject();
            JSONArray outputArray = new JSONArray();

            for (Object obj : lessons) {
                JSONObject lesson = (JSONObject) obj;
                JSONObject time = (JSONObject) lesson.get("idopont");

                System.out.println("------------------------------");
                System.out.println("ID: " + lesson.get("id"));
                System.out.println("Típus: " + lesson.get("tipus"));
                System.out.println("Tárgy: " + lesson.get("targy"));
                System.out.println("Nap: " + time.get("nap"));
                System.out.println("Idő: " + time.get("tol") + " - " + time.get("ig"));
                System.out.println("Helyszín: " + lesson.get("helyszin"));
                System.out.println("Oktató: " + lesson.get("oktato"));
                System.out.println("Szak: " + lesson.get("szak"));
                System.out.println("------------------------------\n");

                JSONObject outLesson = new JSONObject();
                outLesson.put("id", lesson.get("id"));
                outLesson.put("tipus", lesson.get("tipus"));
                outLesson.put("targy", lesson.get("targy"));
                outLesson.put("idopont", time);
                outLesson.put("helyszin", lesson.get("helyszin"));
                outLesson.put("oktato", lesson.get("oktato"));
                outLesson.put("szak", lesson.get("szak"));

                outputArray.add(outLesson);
            }

            JSONObject wrapper = new JSONObject();
            wrapper.put("ora", outputArray);
            outputRoot.put("XGBT95_orarend", wrapper);

            File outFile = new File("XGBT95_1119/jsonparsexgbt95/orarendXGBT951.json");
            FileWriter writer = new FileWriter(outFile);
            writer.write(outputRoot.toJSONString());
            writer.close();

            System.out.println("Új fájl létrehozva: " + outFile.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}