package com.ep.testing.tests;

import com.ep.testing.pages.ConcertsPage;
import com.ep.testing.pages.HomePage;
import com.ep.testing.pages.TicketsPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConcertsPageTests {
    public WebDriver driver;
    private HomePage homePage;
    private TicketsPage ticketsPage;
    private ConcertsPage concertsPage;

    private static String CONCERT_NAME="SHAKIRA";
    @Before
    public void logInTheApplication() throws InterruptedException {
//        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver\\geckodriver.exe");
//        driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "D://EP_PROIECT//EP-testing//testing//src//main//resources//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:6789");
        homePage = new HomePage(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(
                ExpectedConditions.presenceOfElementLocated(By.tagName("h1"))
        );
        concertsPage = homePage.discoverMore();
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void theConcertExistThrowList() throws InterruptedException {
        Thread.sleep(500);
        System.out.println(concertsPage.getConcertsName());
        Assert.assertTrue("The concert does not exist",concertsPage.getConcertsName().contains(CONCERT_NAME));
    }

    @Test
    public void goToTicketsPageOfConcert() throws InterruptedException {
        Thread.sleep(500);
        TicketsPage ticketsPage=concertsPage.getTicketsPageTWO(2);
        Thread.sleep(1000);
        Assert.assertTrue("It is not tickets page",ticketsPage.getStage().isDisplayed());
        Assert.assertEquals("An ticket is already selected","0",ticketsPage.getNumberOfSelected());
    }


}
