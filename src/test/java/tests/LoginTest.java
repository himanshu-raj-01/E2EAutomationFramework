package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ExcelUtil;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {

        String path = System.getProperty("user.dir")
                + "/src/test/resources/testdata/LoginData.xlsx";

        return ExcelUtil.getTestData(path, "Login");
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password, String expectedResult) {

        // pass driver & wait
        LoginPage login = new LoginPage(driver, wait);

        login.enterUsername(username)
             .enterPassword(password)
             .clickLogin();

        if (expectedResult.equalsIgnoreCase("success")) {

            Assert.assertTrue(
                    driver.getCurrentUrl().contains("inventory"),
                    "Login should navigate to inventory page"
            );

        } else {

            Assert.assertTrue(
                    login.getErrorMessage().contains("Epic sadface"),
                    "Error message not displayed for invalid login"
            );
        }
    }
}