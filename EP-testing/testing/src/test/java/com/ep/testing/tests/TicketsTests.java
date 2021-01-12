package com.ep.testing.tests;

import com.ep.testing.pages.ConcertsPage;
import com.ep.testing.pages.HomePage;
import com.ep.testing.pages.LoginPage;
import com.ep.testing.pages.TicketsPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TicketsTests {
    public WebDriver driver;
    private HomePage homePage;
    private TicketsPage ticketsPage;
    LoginPage loginPage;
    private ConcertsPage concertsPage;

    private static String CONCERT_NAME = "SHAKIRA";
    private static String LOGIN_PAGE_TITLE = "Please Log in";
    private static String RAND = "6";
    private static String LOC_1 = "6";
    private static String LOC_2 = "7";
    private static String LOC_3 = "8";
    private static String VALID_EMAIL = "alina_pavel98@yahoo.com";
    private static String VALID_PASSWORD = "khffkhghk";
    public static String CONCERTS_PAGE_TITLE = "Choose your favourite artist and come see him perform live";
    public static String HOME_PAGE_TITLE = "Get your tickets";

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
        Thread.sleep(500);
        ticketsPage = homePage.buyNow();
        Thread.sleep(500);
        wait.until(
                ExpectedConditions.visibilityOf(ticketsPage.getStage())
        );
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void reservedTicketsOnly() throws Exception {
        Assert.assertEquals("An ticket is already selected", "0", ticketsPage.getNumberOfSelected());
        ticketsPage.tableSize(RAND, LOC_1);
        ticketsPage.tableSize(RAND, LOC_2);
        ticketsPage.tableSize(RAND, LOC_3);
        String reserved = "6/6; 6/8;";
        Thread.sleep(1000);
        Assert.assertEquals("The seats was not  reserved", reserved, ticketsPage.getLocuriRezervate());
    }

    @Test
    public void getTotalReserved() throws Exception {
        Assert.assertEquals("An ticket is already selected", "0", ticketsPage.getNumberOfSelected());
        ticketsPage.tableSize(RAND, LOC_1);
        ticketsPage.tableSize(RAND, LOC_2);
        ticketsPage.tableSize(RAND, LOC_3);
        Thread.sleep(1000);
        Assert.assertEquals("The seats was not  reserved", "30", ticketsPage.getTotalReserved());
    }

    @Test
    public void reservedTickets() throws Exception {
        Assert.assertEquals("An ticket is already selected", "0", ticketsPage.getNumberOfSelected());
        ticketsPage.tableSize(RAND, LOC_1);
        ticketsPage.tableSize(RAND, LOC_2);
        ticketsPage.tableSize(RAND, LOC_3);
        Thread.sleep(1000);
        loginPage = ticketsPage.reserveTickets();
        Thread.sleep(1000);
        Assert.assertEquals("It was not redirected to log in page", LOGIN_PAGE_TITLE, loginPage.getTitlePage());
        loginPage.fllInCredentials(VALID_EMAIL, VALID_PASSWORD);
        Thread.sleep(1000);
        Assert.assertEquals("The log in was not succefully!", HOME_PAGE_TITLE, homePage.getTitlePage());
    }

    @Test
    public void cancelReservation() throws Exception {
        Assert.assertEquals("An ticket is already selected", "0", ticketsPage.getNumberOfSelected());
        ticketsPage.tableSize(RAND, LOC_1);
        ticketsPage.tableSize(RAND, LOC_2);
        ticketsPage.tableSize(RAND, LOC_3);
        Thread.sleep(1000);
        concertsPage = ticketsPage.cancel();
        Thread.sleep(1000);
        Assert.assertEquals("It was not redirected to log in page", CONCERTS_PAGE_TITLE, concertsPage.getTitlePage());

    }

}
