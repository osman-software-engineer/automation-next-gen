package org.osmanacademy.applicationundertest.pageobjects;

import org.openqa.selenium.By;
import org.osmanacademy.webbrowser.WebBrowserAutomation;

public class ProductsPageObject {

    private WebBrowserAutomation webBrowserAutomation;
    private By pageTitle = By.xpath("//*[contains(@class,'title') and contains(text(),'Products')]");

    private InventoryPageObject inventoryPageObject;
    public ProductsPageObject(WebBrowserAutomation webBrowserAutomation) {
        this.webBrowserAutomation = webBrowserAutomation;
        this.inventoryPageObject = new InventoryPageObject(webBrowserAutomation);
    }


}
