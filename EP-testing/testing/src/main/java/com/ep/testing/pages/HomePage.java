package com.ep.testing.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    protected WebDriver driver;

    @FindBy(xpath = "//*[@id=\"h2\"]")
    private WebElement homePageTitle;

    @FindBy(className = "button1")
    private WebElement buyTicketsButton;

    @FindBy(className = "button2")
    private WebElement discoverMoreButton;

    @FindBy(xpath = "/html/body/ul/li[4]/a")
    private WebElement concertsTab;

    @FindBy(xpath = "/html/body/ul/li[3]/a")
    private WebElement galleryTab;

    @FindBy(xpath = "/html/body/ul/li[2]/a")
    private WebElement aboutTab;

    @FindBy(xpath = "/html/body/ul/li[1]/a")
    private WebElement contactTab;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitlePage() {
        return homePageTitle.getText();
    }

    public TicketsPage buyNow() {
        buyTicketsButton.click();
        return new TicketsPage(driver);
    }

    public ConcertsPage goToConcertsPage() {
        concertsTab.click();
        return new ConcertsPage(driver);
    }

    public GalleryPage goToGalleryPage() {
        galleryTab.click();
        return new GalleryPage(driver);

    }

    public AboutPage goToAboutPage() {
        aboutTab.click();
        return new AboutPage(driver);

    }

    public ConcertsPage goToConcertPage() {
        concertsTab.click();
        return new ConcertsPage(driver);
    }

    public ConcertsPage discoverMore() {
        discoverMoreButton.click();
        return new ConcertsPage(driver);
    }


}
