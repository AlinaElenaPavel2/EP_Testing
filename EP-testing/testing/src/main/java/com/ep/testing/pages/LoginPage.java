package com.ep.testing.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    protected WebDriver driver;


    @FindBy(id = "email")
    public WebElement emailField;

    @FindBy(id = "password")
    protected WebElement passwordField;

    @FindBy(xpath = "/html/body/form/div/input[3]")
    protected WebElement logInButton;

    @FindBy(xpath = "/html/body/a/button")
    protected WebElement registerButton;

    @FindBy(tagName = "h1")
    protected WebElement title;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage fllInCredentials(String email, String password) throws InterruptedException {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        Thread.sleep(2000);
        logInButton.click();
        Thread.sleep(2000);
        return new HomePage(driver);
    }

    public RegisterPage goToRegister() throws InterruptedException {
        registerButton.click();
        Thread.sleep(2000);
        return new RegisterPage(driver);
    }

    public String getTitlePage()
    {
        return title.getText();
    }

}
