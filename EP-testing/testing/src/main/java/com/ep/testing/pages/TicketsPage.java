package com.ep.testing.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TicketsPage {
    private WebDriver driver;

    @FindBy(className = "scena")
    private WebElement stage;

    @FindBy(id = "numarloc")
    private WebElement selected;

    @FindBy(id = "mesajlocuri")
    private WebElement locuriRezervarte;

    @FindBy(tagName = "tbody")
    private WebElement locuri;

    @FindBy(id = "numarloct")
    private WebElement totalResearved;

    @FindBy(xpath = "/html/body/a[2]/button")
    private WebElement reserve;

    @FindBy(xpath = "/html/body/a[1]/button")
    private WebElement cancel;



    public TicketsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getStage() {
        return stage;
    }

    public String getNumberOfSelected() {
        return selected.getText();
    }

    public String getLocuriRezervate() {
        return locuriRezervarte.getText();
    }

    public void select(String rand, String loc) throws Exception {
        int randInt=Integer.parseInt(rand)+1;
        int locInt=Integer.parseInt(loc);
        int rows = driver.findElements(By.xpath("//*[@id=\"locuri\"]/tbody/tr")).size() - 1;
        String path = "//*[@id=\"locuri\"]/tbody/tr[" + rand + "]//td";
        int columns = driver.findElements(By.xpath(path)).size();
        if(randInt<0 || randInt >rows)
        {
            throw new Exception("The row does not exist!");
        }
        if(locInt<0 || locInt >columns)
        {
            throw new Exception("The column does not exist!");
        }
        String xpathLoc = "//*[@id=\"locuri\"]/tbody/tr[" + randInt + "]/td[" + loc + "]";
        WebElement webElement = driver.findElement(By.xpath(xpathLoc));
            Thread.sleep(2000);
            webElement.click();


    }

    public String getTotalReserved()
    {
        return totalResearved.getText();
    }

    public LoginPage reserveTickets()
    {
        reserve.click();
        return new LoginPage(driver);
    }

    public ConcertsPage cancel()
    {
        cancel.click();
        return new ConcertsPage(driver);
    }

    public String selected()
    {
        return selected.getText();
    }
}
