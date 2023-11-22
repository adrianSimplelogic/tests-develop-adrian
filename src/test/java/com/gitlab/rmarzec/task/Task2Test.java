package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;


public class Task2Test {


    @Test
    public void Task2Test() {
        DriverFactory driverFactory = new DriverFactory();
        WebDriver webDriver = driverFactory.initDriver();

        //mój kod - Adrian
        webDriver.get("https://pl.wikipedia.org/wiki/Wiki");

        for (WebElement element : webDriver.findElements(By.cssSelector("a[lang]"))){
            if (element.getAttribute("lang").contains("en")) {
                System.out.println(element.getAttribute("textContent") + " " + element.getAttribute("href"));
            } else if (!element.getAttribute("lang").contains("en")) {
                System.out.println(element.getAttribute("textContent"));
            }
        }

        webDriver.quit();
    }
}
