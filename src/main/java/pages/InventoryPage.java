package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryPage {

    private WebDriverWait wait;

    public InventoryPage(WebDriver driver, WebDriverWait wait) {
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addBackpackBtn;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    public InventoryPage addBackpackToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addBackpackBtn)).click();
        return this;
    }

    public InventoryPage clickCartIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
        return this;
    }
}