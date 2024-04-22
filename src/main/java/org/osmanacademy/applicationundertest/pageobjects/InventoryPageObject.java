package org.osmanacademy.applicationundertest.pageobjects;

import org.openqa.selenium.By;
import org.osmanacademy.webbrowser.WebBrowserAutomation;

public class InventoryPageObject {

    private WebBrowserAutomation webBrowserAutomation;
    private By itemNameLocator = By.cssSelector(".inventory_item_name");
    private By itemDescriptionLocator = By.cssSelector(".inventory_item_desc");
    private By itemPriceLocator = By.cssSelector(".inventory_item_price");
    private By itemImageLocator = By.cssSelector(".inventory_item_img");
    private By addToCartButtonLocator = By.id("add-to-cart-sauce-labs-backpack");

  public InventoryPageObject(WebBrowserAutomation webBrowserAutomation) {
        this.webBrowserAutomation = webBrowserAutomation;
    }
    /*
    public String getItemName() {
        return webBrowserAutomation.;
    }

    public String getItemDescription() {
        return driver.findElement(itemDescriptionLocator).getText();
    }

    public String getItemPrice() {
        return driver.findElement(itemPriceLocator).getText();
    }

    public String getItemImageUrl() {
        return driver.findElement(itemImageLocator).getAttribute("src");
    }

    public void clickAddToCart() {
        driver.findElement(addToCartButtonLocator).click();
    }*/
}
