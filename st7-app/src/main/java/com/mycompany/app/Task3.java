package com.mycompany.app;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task3 {
    public static void run() {
        System.setProperty("webdriver.chrome.driver", "Z:\\Coding\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        try {
            String url = "https://api.open-meteo.com/v1/forecast"
                       + "?latitude=56&longitude=44"
                       + "&hourly=temperature_2m,rain"
                       + "&current=cloud_cover"
                       + "&timezone=Europe%2FMoscow"
                       + "&forecast_days=1"
                       + "&wind_speed_unit=ms";

            driver.get(url);

            String jsonText = driver.findElement(By.tagName("body")).getText();

            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(jsonText);

            JSONObject current = (JSONObject) root.get("current");
            System.out.println("Текущая облачность: " + current.get("cloud_cover") + "%");

            JSONObject hourly = (JSONObject) root.get("hourly");
            JSONArray times = (JSONArray) hourly.get("time");
            JSONArray temps = (JSONArray) hourly.get("temperature_2m");
            JSONArray rains = (JSONArray) hourly.get("rain");

            System.out.println("Часовой прогноз для Нижнего Новгорода:");
            for (int i = 0; i < times.size(); i++) {
                String time = (String) times.get(i);
                Number temp = (Number) temps.get(i);
                Number rain = (Number) rains.get(i);
                System.out.printf("%s — T=%.1f °C, Rain=%.2f mm%n",
                                  time, temp.doubleValue(), rain.doubleValue());
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
