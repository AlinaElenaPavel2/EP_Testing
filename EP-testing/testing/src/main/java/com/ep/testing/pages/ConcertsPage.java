package com.ep.testing.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class ConcertsPage {
    protected WebDriver driver;

    @FindBy(id = "gallery")
    private WebElement titlePage;

    @FindBy(xpath = "/html/body/div/div")
    List<WebElement> concerts;


    @FindBy(xpath = "/html/body/div/div/a")
    List<WebElement> concertsTickets;


    public ConcertsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitlePage() {
        return titlePage.getText();
    }

    public List<String> getConcertsName() throws InterruptedException {
        Actions hover = new Actions(driver);
        List<String> concertsName = new ArrayList<>();
        for (WebElement concert : concerts
        ) {
            Thread.sleep(1000);
            hover.moveToElement(concert);
            hover.build();
            hover.perform();
            Thread.sleep(500);
            WebElement details=concert.findElement(By.className("title1"));
            concertsName.add(details.getText());
        }
        return concertsName;
    }

    public TicketsPage getTicketsPage(String concertName) throws InterruptedException {
        int index = 0;
        List<String> concertsName = this.getConcertsName();
        for (String concert : concertsName
        ) {
            if (concert.equals(concertName)) {
                index = concertsName.indexOf(concert);
            }
        }
        concertsTickets.get(index).click();
        Thread.sleep(500);
        return new TicketsPage(driver);
    }

    public TicketsPage getTicketsPageTWO(int index) throws InterruptedException {
        int indexT = index;
        Thread.sleep(1000);
        concertsTickets.get(indexT).click();
        Thread.sleep(1000);
        return new TicketsPage(driver);
    }
}
