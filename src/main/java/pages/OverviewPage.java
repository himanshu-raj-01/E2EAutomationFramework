package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OverviewPage {

    private WebDriverWait wait;

    public OverviewPage(WebDriver driver, WebDriverWait wait) {
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "finish")
    private WebElement finishBtn;

    public OverviewPage clickFinish() {
        wait.until(ExpectedConditions.elementToBeClickable(finishBtn)).click();
        return this;
    }
}