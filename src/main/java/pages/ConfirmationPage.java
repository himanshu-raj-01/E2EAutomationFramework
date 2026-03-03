package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConfirmationPage {

    private WebDriverWait wait;

    public ConfirmationPage(WebDriver driver, WebDriverWait wait) {
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "complete-header")
    private WebElement confirmationMessage;

    public String getConfirmationMessage() {
        return wait.until(ExpectedConditions.visibilityOf(confirmationMessage)).getText();
    }
}