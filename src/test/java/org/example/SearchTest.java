package org.example;

import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchTest {

    Properties properties;

    WebDriver driver = null;

    @BeforeClass
    @SneakyThrows
    public void beforeClass() {
        properties = new Properties();
        try (BufferedReader reader = new BufferedReader(new FileReader("config.properties"))) {
            properties.load(reader);
            File file = new File(properties.getProperty("path"));
            String driverName = properties.getProperty("driver");
            System.setProperty(driverName, file.getAbsolutePath());

            if (driverName.contains("chrome")) {
                driver = new ChromeDriver();
            } else if (driverName.contains("edge")) {
                driver = new EdgeDriver();
            }

            assertThat(driver).isNotNull();
        }
    }

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }

    @Test
    @SneakyThrows
    public void startProgrammingTest() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.navigate().to("http://berkut.mk.ua/test2022/");
        System.out.println(driver.getTitle());

        WebElement name = driver.findElement(By.xpath("//*[@name='fio']"));
        name.sendKeys("Student");
        Thread.sleep(1000);

        WebElement group = driver.findElement(By.id("group"));
        group.sendKeys("1111");
        Thread.sleep(1000);

        WebElement button = driver.findElement(By.className("accept"));
        button.click();
        Thread.sleep(1000);

        List<WebElement> answers = driver.findElements(By.className("answer"));
        for (WebElement answer : answers) {
            System.out.println(answer.getText());
        }

        //  button[1]
    }

    @Test
    public void openYouTube() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.youtube.com");

        driver.navigate().refresh();
        System.out.println(driver.getTitle());
    }

    @Test
    @SneakyThrows
    public void openBrowserInTest() {

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        driver.navigate().to("https://www.google.com/");

        System.out.println(driver.getTitle());

//        WebElement queryString = driver.findElement(By.name("q"));
//        queryString.sendKeys("Selenium Java");
////        queryString.sendKeys(Keys.ENTER);
//
//        WebElement searchButton = driver.findElement(By.name("btnK"));
//        searchButton.click();
//
//        System.out.println(driver.getTitle());
//
//        driver.navigate().back();
//        Thread.sleep(1000);
//        System.out.println(driver.getTitle());

        WebElement element = driver.findElement(By.xpath("//a[@class='gb_t']"));
        System.out.println(element.getAttribute("href"));
        System.out.println(element.getText());
    }
}
