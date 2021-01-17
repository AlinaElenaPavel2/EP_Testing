package com.ep.testing.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    protected WebDriver driver;

    @FindBy(xpath = "/html/body/form/div/h1")
    protected WebElement title;

    @FindBy(id = "firstname")
    protected WebElement firstName;
    @FindBy(id = "lastname")
    protected WebElement lastName;
    @FindBy(id = "email")
    protected WebElement email;
    @FindBy(id = "password")
    protected WebElement password;
    @FindBy(id = "phonenumber")
    protected WebElement phone;
    @FindBy(xpath = "/html/body/form/div/input[9]")
    protected WebElement registerButton;

    public RegisterPage(WebDriver driver) {
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitlePage()
    {
        return title.getText();
    }


    public void fllInCredentials(String firstname,String lastname,String email,String password, String phone) throws InterruptedException {
        this.firstName.sendKeys(firstname);
        this.lastName.sendKeys(lastname);
        this.password.sendKeys(password);
        this.email.sendKeys(email);
        this.phone.sendKeys(phone);
    }

    public LoginPage registered() throws InterruptedException {
        registerButton.click();
        Thread.sleep(2000);
        return new LoginPage(driver);
    }

}
