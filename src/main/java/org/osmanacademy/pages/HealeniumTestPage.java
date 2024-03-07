package org.osmanacademy.pages;

import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HealeniumTestPage {

    private SelfHealingDriver driver;

    // Constructor
    public HealeniumTestPage(SelfHealingDriver driver) {
        this.driver = driver;
    }

    // Locators
    private final By generateAlertButton = By.id("submit_alert");
    private final By fieldIdAndTagName = By.id("change_id");
    private final By fieldClassName = By.id("change_className");
    private final By fieldTagName = By.id("change_below_element");
    private final By textAreaDisabled = By.id("change_enabled");
    private final By checkboxDisabled = By.id("change_disabled");
    private final By checkboxChecked = By.id("change_checked");
    private final By fieldName = By.name("change_name");
    private final By linkText = By.id("change_links");
    private final By changeLocatorsButton = By.id("Submit");
    // Add more locators for other elements as needed

    // Methods to interact with elements
    public void clickGenerateAlert() {
        driver.findElement(generateAlertButton).click();
    }

    public void enterTextInFieldIdAndTagName(String text) {
        driver.findElement(fieldIdAndTagName).sendKeys(text);
    }

    public void enterTextInFieldClassName(String text) {
        driver.findElement(fieldClassName).sendKeys(text);
    }

    public void enterTextInFieldTagName(String text) {
        driver.findElement(fieldTagName).sendKeys(text);
    }

    public void clickChangeLocators() {
        driver.findElement(changeLocatorsButton).click();
    }

    public String getLinkHref() {
        return driver.findElement(linkText).getAttribute("href");
    }

    // Add more methods to interact with other elements as needed
}
