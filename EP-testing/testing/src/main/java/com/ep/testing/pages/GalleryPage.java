package com.ep.testing.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GalleryPage {
    protected WebDriver driver;

    @FindBy(id = "gallery")
    private WebElement titlePage;

    public GalleryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitlePage()
    {
        return titlePage.getText();
    }
}
