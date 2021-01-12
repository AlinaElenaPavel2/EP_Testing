package com.ep.testing.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AboutPage {
    protected WebDriver driver;

    @FindBy(id = "about")
    private WebElement titlePage;

    @FindBy(className = "button5")
    private WebElement moreDetailsButton;

    @FindBy(xpath = "//*[@id=\"demo\"]/h1")
    private WebElement firstTitle;

    @FindBy(xpath = "//*[@id=\"demo\"]/p[1]")
    private WebElement charityPharagraph;


    public AboutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitlePage()
    {
        return titlePage.getText();
    }

    public AboutPage getDetails()
    {
        moreDetailsButton.click();
        return new AboutPage(driver);
    }

    public String getFirstTitle()
    {
        return firstTitle.getText();
    }
    public WebElement getFirstParagraph()
    {
        return charityPharagraph;
    }

}
