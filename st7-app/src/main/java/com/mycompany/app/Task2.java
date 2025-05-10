package com.mycompany.app;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task2 {
    public static void run() {
        System.setProperty("webdriver.chrome.driver", "Z:\\Coding\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://api.ipify.org/?format=json");

            String jsonText = driver.findElement(By.tagName("body")).getText();

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonText);

            String ip = (String) obj.get("ip");
            System.out.println("Ваш IP-адрес: " + ip);

        } catch (Exception e) {
            System.err.println("Ошибка при получении IP:");
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
