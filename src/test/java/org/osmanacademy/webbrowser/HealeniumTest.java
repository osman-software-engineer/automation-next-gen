package org.osmanacademy.webbrowser;


import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.WebDriver;
import org.osmanacademy.pages.HealeniumTestPage;
import org.testng.annotations.Test;

public class HealeniumTest {
    @Test
    public void testXpathSpecialCharacter() throws Exception {
        WebBrowser healeniumTestApp = new WebBrowserImpl();
        SelfHealingDriver selfHealingDriver =  healeniumTestApp.openBrowser();
        healeniumTestApp.maximize();
        healeniumTestApp.openUrl("https://elenastepuro.github.io/test_env/index.html");
        HealeniumTestPage healeniumTestPage = new HealeniumTestPage(selfHealingDriver);
        healeniumTestPage.enterTextInFieldIdAndTagName("Text before Locator Change");
        healeniumTestPage.clickChangeLocators();
        healeniumTestPage.enterTextInFieldIdAndTagName("Text after Locator Change");
        healeniumTestApp.closeBrowser();

    }
}
