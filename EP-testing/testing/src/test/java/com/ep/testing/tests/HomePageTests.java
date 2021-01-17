package com.ep.testing.tests;

import com.ep.testing.pages.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePageTests {
    private static WebDriver driver;
    private HomePage homePage;
    private TicketsPage ticketsPage;
    private LoginPage loginPage;

    public static String CONCERTS_PAGE_TITLE = "Choose your favourite artist and come see him perform live";
    public static String GALLERY_PAGE_TITLE = "See the moments we shared with your favourite artists";
    public static String ABOUT_PAGE_TITLE = "Find out more about us";
    private static String LOGIN_PAGE_TITLE = "Please Log in";
    public static String ABOUT_PAGE_SUBTITLE = "We support charity";
    private static String RAND_6 = "6";
    private static String RAND_7 = "7";
    private static String LOC_6 = "6";
    private static String LOC_7 = "7";
    private static String LOC_8 = "8";
    private static String LOC_9 = "9";
    private static String LOC_10 = "10";
    private static String VALID_EMAIL = "alina_pavel98@yahoo.com";
    private static String VALID_PASSWORD = "777777";
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
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void goToTicketsPage() throws InterruptedException {
        Thread.sleep(500);
        ticketsPage=homePage.buyNow();
        Thread.sleep(500);
        Assert.assertEquals("Are already selected seats!", "0", ticketsPage.getNumberOfSelected());
    }

    @Test
    public void discoverMoreGoesToConcertsPage() throws InterruptedException {
        Thread.sleep(500);
        ConcertsPage concertsPage = homePage.discoverMore();
        Thread.sleep(1000);
        Assert.assertEquals("You are not on Concerts Page", CONCERTS_PAGE_TITLE, concertsPage.getTitlePage());
    }


    @Test
    public void goToGallery() throws InterruptedException {
        Thread.sleep(500);
        GalleryPage galleryPage = homePage.goToGalleryPage();
        Thread.sleep(1000);
        Assert.assertEquals("You are not on Gallery Page", GALLERY_PAGE_TITLE, galleryPage.getTitlePage());
    }


    @Test
    public void goToDetails() throws InterruptedException {
        Thread.sleep(500);
        AboutPage aboutPage = homePage.goToAboutPage();
        Thread.sleep(1000);
        Assert.assertEquals("You are not on About Page", ABOUT_PAGE_TITLE, aboutPage.getTitlePage());
        aboutPage.getDetails();
        Assert.assertTrue("The first paragraph is not displayed", aboutPage.getFirstParagraph().isDisplayed());
    }

    @Test
    public void reserveTicketsSuccessfully() throws Exception {
        Thread.sleep(500);
        ticketsPage=homePage.buyNow();
        Thread.sleep(500);
        Assert.assertEquals("Are already selected seats!", "0", ticketsPage.getNumberOfSelected());

        ticketsPage.select(RAND_6, LOC_6);
        ticketsPage.select(RAND_6, LOC_7);
        ticketsPage.select(RAND_6, LOC_8);
        ticketsPage.select(RAND_7, LOC_9);
        ticketsPage.select(RAND_7, LOC_10);
        ticketsPage.select(RAND_6, LOC_6);
        ticketsPage.select(RAND_6, LOC_7);
        String reserved = "6/8; 7/9; 7/10;";
        Thread.sleep(1000);
        Assert.assertEquals("The seats was not  reserved", reserved, ticketsPage.getLocuriRezervate());
        Assert.assertEquals("The seats was not  reserved", "3", ticketsPage.selected());
        loginPage=ticketsPage.reserveTickets();
        Thread.sleep(1000);
        Assert.assertEquals("It was not redirected to log in page", LOGIN_PAGE_TITLE, loginPage.getTitlePage());
        loginPage.fllInCredentials(VALID_EMAIL, VALID_PASSWORD);
        loginPage.logIn();
        Thread.sleep(1000);
        Assert.assertEquals("The log in was not succefully!", HOME_PAGE_TITLE, homePage.getTitlePage());
    }
}


