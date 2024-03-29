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

public class LoginTests {
    private LoginPage loginPage;
    private HomePage homePage;
    private RegisterPage registerPage;
    private static WebDriver driver;
    private static String VALID_EMAIL = "alina_pavel98@yahoo.com";
    private static String VALID_PASSWORD = "777777";
    private static String HOME_PAGE_TITLE = "Get your tickets";
    private static String REGISTER_PAGE_TITLE = "Register";

    private static String INVALID_EMAIL = "alina_pavel98@yahoo.comm";
    private static String INVALID_PASSWORD = "khffkhghk";
    private static String ERROR_MESSAGE="Wrong email!";

    @Before
    public void logInTheApplication() throws InterruptedException {
        //        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver\\geckodriver.exe");
//        driver = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "D://EP_PROIECT//EP-testing//testing//src//main//resources//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:6789/login");
        loginPage = new LoginPage(driver);
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void loginWithValidCredentials() throws InterruptedException {
        loginPage.fllInCredentials(VALID_EMAIL, VALID_PASSWORD);
        homePage=loginPage.logIn();
        Assert.assertEquals("The log in was not succefully!", HOME_PAGE_TITLE, homePage.getTitlePage());
    }

    @Test
    public void loginWithInvalidCredentials() throws InterruptedException {
        loginPage.fllInCredentials(INVALID_EMAIL, VALID_PASSWORD);
        loginPage.logIn();
        Assert.assertEquals("The log in was succefully!", ERROR_MESSAGE, loginPage.getErrorMessage());
    }

    @Test
    public void goToRegisterIntoApplication() throws InterruptedException {
        registerPage = loginPage.goToRegister();
        Assert.assertEquals("The log in was succefully!", REGISTER_PAGE_TITLE, registerPage.getTitlePage());
    }

}
