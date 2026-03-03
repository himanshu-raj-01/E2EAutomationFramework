package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

    private WebDriverWait wait;

    public CheckoutPage(WebDriver driver, WebDriverWait wait) {
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "first-name")
    private WebElement firstName;

    @FindBy(id = "last-name")
    private WebElement lastName;

    @FindBy(id = "postal-code")
    private WebElement postalCode;

    @FindBy(id = "continue")
    private WebElement continueBtn;

    public CheckoutPage enterFirstName(String fname) {
        wait.until(ExpectedConditions.visibilityOf(firstName)).sendKeys(fname);
        return this;
    }

    public CheckoutPage enterLastName(String lname) {
        wait.until(ExpectedConditions.visibilityOf(lastName)).sendKeys(lname);
        return this;
    }

    public CheckoutPage enterPostalCode(String code) {
        wait.until(ExpectedConditions.visibilityOf(postalCode)).sendKeys(code);
        return this;
    }

    public CheckoutPage clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
        return this;
    }
}