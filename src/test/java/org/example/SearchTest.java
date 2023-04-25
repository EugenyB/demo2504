package org.example;

import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.Duration;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchTest {

    Properties properties;

    @BeforeClass
    @SneakyThrows
    public void beforeClass() {
        properties = new Properties();
        try (BufferedReader reader = new BufferedReader(new FileReader("config.properties"))) {
            properties.load(reader);
        }
    }

    @Test
    public void openBrowserInTest() {
        File file = new File(properties.getProperty("path"));
        String driverName = properties.getProperty("driver");
        System.setProperty(driverName, file.getAbsolutePath());
        WebDriver driver = null;
        if (driverName.contains("chrome")) {
            driver = new ChromeDriver();
        } else if (driverName.contains("edge")) {
            driver = new EdgeDriver();
        }
        assertThat(driver).isNotNull();

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        driver.navigate().to("https://www.google.com/");

        WebElement queryString = driver.findElement(By.name("q"));
        queryString.sendKeys("Selenium Java");
//        queryString.sendKeys(Keys.ENTER);

        WebElement searchButton = driver.findElement(By.name("btnK"));
        searchButton.click();
    }
}
