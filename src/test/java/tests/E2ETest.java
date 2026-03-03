package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.*;

public class E2ETest extends BaseTest {

    @Test
    public void completeOrderTest() {

        LoginPage login = new LoginPage(driver, wait);

        login.enterUsername("standard_user")
             .enterPassword("secret_sauce")
             .clickLogin();

        InventoryPage inventory = new InventoryPage(driver, wait);
        inventory.addBackpackToCart()
                 .clickCartIcon();

        CartPage cart = new CartPage(driver, wait);
        Assert.assertEquals(cart.getProductName(), "Sauce Labs Backpack");
        cart.clickCheckout();

        CheckoutPage checkout = new CheckoutPage(driver, wait);
        checkout.enterFirstName("Himanshu")
                .enterLastName("Raj")
                .enterPostalCode("751024")
                .clickContinue();

        OverviewPage overview = new OverviewPage(driver, wait);
        overview.clickFinish();

        ConfirmationPage confirmation = new ConfirmationPage(driver, wait);
        Assert.assertEquals(confirmation.getConfirmationMessage(),
                "Thank you for your order!");
    }
}