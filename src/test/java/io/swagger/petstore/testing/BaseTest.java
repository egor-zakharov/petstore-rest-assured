package io.swagger.petstore.testing;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class BaseTest {

    @BeforeAll
    public static void setUp() {
        try (InputStream input = new FileInputStream("./src/main/resources/settings.properties")) {
            Properties settingsProp = new Properties();
            settingsProp.load(input);
            RestAssured.baseURI = (settingsProp.getProperty("base.uri"));
            RestAssured.basePath = (settingsProp.getProperty("base.path"));
            RestAssured.defaultParser = Parser.JSON;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void tearDown() {
        Properties envProp = new Properties();
        envProp.setProperty("author", "Egor Zakharov");
        envProp.setProperty("github", "https://github.com/egor-zakharov/");
        envProp.setProperty("email", "zaharov.e.d@ya.ru");
        envProp.setProperty("telegram", "@EgorZakharov");
        File resultsFolder = new File("./build/allure-results");
        if (!resultsFolder.exists()) {
            resultsFolder.mkdirs();
        }
        try (OutputStream out = new FileOutputStream("./build/allure-results/environment.properties")) {
            envProp.store(out, "environment.properties");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
