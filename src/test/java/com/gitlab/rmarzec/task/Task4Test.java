package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import com.gitlab.rmarzec.model.YTTile;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class Task4Test {


    @Test
    public void Task4Test() {
        DriverFactory driverFactory = new DriverFactory();
        WebDriver webDriver = driverFactory.initDriver();

        //Lista kafelkow
        List<YTTile> ytTileList = new ArrayList<YTTile>();

        //mój kod - Adrian
        webDriver.get("https://www.youtube.com/");
        new WebDriverWait(webDriver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label='Accept the use of cookies and other data for the purposes described']"))).click();
        new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ytd-rich-grid-renderer")));

        List<WebElement> tiles = webDriver.findElements(By.xpath("//ytd-rich-grid-renderer//ytd-rich-grid-media"));
        for (int i = 0; i < 12; i++) {
            WebElement tileElement = tiles.get(i);

            YTTile ytTile = new YTTile();

            WebElement title = tileElement.findElement(By.cssSelector("#video-title"));
            ytTile.setTitle(title.getText());

            WebElement channel = tileElement.findElement(By.cssSelector("#text a"));
            ytTile.setChannel(channel.getText());

            List<WebElement> lengths = tileElement.findElements(By.cssSelector("#time-status span"));
            if(!lengths.isEmpty()){
                WebElement lengthElement = lengths.get(0);
                ytTile.setLength(lengthElement.getAttribute("textContent"));
            } else {
                ytTile.setLength("LIVE");
            }
            ytTileList.add(ytTile);
        }

        for(YTTile el:ytTileList){
            if(!el.getLength().equals("live")){
                System.out.println("Tytuł: "+el.getTitle());
                System.out.println("Kanał: "+el.getChannel());
                System.out.println("Czas: "+el.getLength());
                System.out.println("");
            }
        }
        webDriver.quit();
    }
}
