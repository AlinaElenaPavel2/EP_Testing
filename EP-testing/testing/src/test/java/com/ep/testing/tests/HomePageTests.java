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

    public static String CONCERTS_PAGE_TITLE = "Choose your favourite artist and come see him perform live";
    public static String GALLERY_PAGE_TITLE = "See the moments we shared with your favourite artists";
    public static String ABOUT_PAGE_TITLE = "Find out more about us";
    public static String ABOUT_PAGE_SUBTITLE = "We support charity";


    @Before
    public void logInTheApplication() throws InterruptedException {
//        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver\\geckodriver.exe");
//        driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "D://EP_PROIECT//EP-testing//testing//src//main//resources//chromedriver.exe");
        WebDriver driver = new ChromeDriver();
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
        Assert.assertEquals("You are not on About Page", ABOUT_PAGE_SUBTITLE, aboutPage.getFirstTitle());
        Assert.assertTrue("The first paragraph is not displayed", aboutPage.getFirstParagraph().isDisplayed());
    }
}


