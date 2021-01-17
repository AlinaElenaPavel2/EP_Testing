package com.ep.testing.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    protected WebDriver driver;


    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(xpath = "/html/body/form/div/input[3]")
    private WebElement logInButton;

    @FindBy(xpath = "/html/body/a/button")
    private WebElement registerButton;

    @FindBy(tagName = "h1")
    private WebElement title;

    @FindBy(xpath = "/html/body/form/div/h3")
    private WebElement errorMessage;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginPage fllInCredentials(String email, String password) throws InterruptedException {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        Thread.sleep(2000);
        return new LoginPage(driver);
    }

    public HomePage logIn() throws InterruptedException {
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
    public String getErrorMessage()
    {
        return errorMessage.getText();
    }
}
