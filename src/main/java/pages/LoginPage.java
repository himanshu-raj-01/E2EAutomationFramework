package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginPage {

    private WebDriverWait wait;

    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    // Constructor (IMPORTANT CHANGE)
    public LoginPage(WebDriver driver, WebDriverWait wait) {
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMessage;

    public LoginPage enterUsername(String user) {
        logger.info("Entering username: " + user);
        wait.until(ExpectedConditions.visibilityOf(username)).sendKeys(user);
        return this;
    }

    public LoginPage enterPassword(String pass) {
        logger.info("Entering password");
        wait.until(ExpectedConditions.visibilityOf(password)).sendKeys(pass);
        return this;
    }

    public LoginPage clickLogin() {
        logger.info("Clicking login button");
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        return this;
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText();
    }
}