package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class Task3Test {

    WebDriverWait wait;
    SoftAssert softAssert;

    @Test
    public void Task3Test() {
        DriverFactory driverFactory = new DriverFactory();
        WebDriver webDriver = driverFactory.initDriver();

        //mój kod Adrian
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));

        webDriver.get("https://www.google.com/");

        webDriver.findElement(By.cssSelector("button[id]:nth-of-type(2)")).click();
        webDriver.findElement(By.cssSelector("div>textarea")).sendKeys("HTML select tag - W3Schools");
        List<WebElement> imLucky = webDriver.findElements(By.name("btnI")); //znajduje dwa elementy, pobieram do listy
        imLucky.get(1).click(); //klikam w drugi, interesujący mnie element

        //softAsercja i ewentualne przekierowanie na właściwy adres WWW
        softAssert = new SoftAssert();
        String currentUrl = webDriver.getCurrentUrl();
        String awaitingUrl = "https://www.w3schools.com/tags/tag_select.asp";
        softAssert.assertTrue(awaitingUrl.equals(currentUrl));
        if (!currentUrl.equals(awaitingUrl)) {
            System.out.println("Aktualny adres na jaki trafiliśmy: " + currentUrl);
            System.out.println("Przekierowanie na adres: " + awaitingUrl);
            webDriver.get(awaitingUrl);
        }

        //W3School
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#accept-choices"))).click();
        webDriver.findElement(By.cssSelector("div.w3-example a")).click(); //znajduje dwa elementy ale nas interesuje pierwszy który zostanie kliknięty
        webDriver.navigate().to("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select"); //przerzucenie focusa na drugą zakładkę przeglądarki

        //iframe
        webDriver.switchTo().frame(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#iframewrapper>iframe"))));
        System.out.println("Nagłówek w iframe: "+webDriver.findElement(By.cssSelector("body[contenteditable='false'] h1")).getText());
        Select carsDropdown = new Select(webDriver.findElement(By.cssSelector("select#cars")));
        carsDropdown.selectByValue("opel");
        System.out.println("Aktualnie wybrany selectElement oraz jesgo wartość: "+carsDropdown.getFirstSelectedOption().getText()+" "+carsDropdown.getFirstSelectedOption().getAttribute("value"));

        webDriver.quit();
    }
}
