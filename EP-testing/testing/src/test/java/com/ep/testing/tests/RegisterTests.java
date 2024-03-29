package com.ep.testing.tests;

import com.ep.testing.pages.HomePage;
import com.ep.testing.pages.LoginPage;
import com.ep.testing.pages.RegisterPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegisterTests {
    private HomePage homePage;
    private RegisterPage registerPage;
    private LoginPage loginPage;
    private static WebDriver driver;

    private static String LAST_NAME = "Pavel";
    private static String FISRT_NAME = "Alina";
    private static String EMAIL = "alina_pavel98@yahoo.com";
    private static String PHONE = "0754563890";
    private static String PASSWORD = "khffkhghk";

    private static String EXISTENT_EMAIL = "alina_pavel98@yahoo.com";
    private static String REGISTER_PAGE_TITLE = "Register";
    private static String LOGIN_PAGE_TITLE = "Please Log in";


    @Before
    public void logInTheApplication() throws InterruptedException {
        //        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver\\geckodriver.exe");
//        driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "D://EP_PROIECT//EP-testing//testing//src//main//resources//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:6789/register");
        registerPage = new RegisterPage(driver);
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void registerSuccessfully() throws InterruptedException {
        registerPage.fllInCredentials(LAST_NAME, FISRT_NAME, EMAIL, PASSWORD, PHONE);
        loginPage=registerPage.registered();
        Assert.assertEquals("The register was not succefully!", LOGIN_PAGE_TITLE, loginPage.getTitlePage());
    }
}
