package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.time.Duration;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://dev.popipro.com/control/login");
        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1)
    public void testValidLogin() {
        loginPage.login("er.prafullgupta@gmail.com", "12345678");

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains("dashboard"));

        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "Login failed");
    }
    @Test(priority = 2)
    public void testInvalidLogin() {
        loginPage.login("test@gmail.com", "12345678");
        String actualErrorMessage = driver.findElement(By.xpath("//span[starts-with(@class,'text-tiny')]")).getText();
        String expectedErrorMessage = "The selected email is invalid.";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message did not match!");
    }
    @Test(priority = 3)
    public void testEmptyUsername() {
        loginPage.login("", "12345678");
        if(driver.getCurrentUrl().contains("dashboard")) {
            Assert.fail("Login successful");
        } else {
            Assert.assertTrue(true);
        }
    }
    @Test(priority = 4)
    public void testEmptyPassword() {
        loginPage.login("user", "");
        if(driver.getCurrentUrl().contains("dashboard")) {
            Assert.fail("Login successful");
        } else {
            Assert.assertTrue(true);
        }}
    @Test(priority = 5)
    public void testEmptyUsernameAndPassword() {
        loginPage.login("", "");
        if(driver.getCurrentUrl().contains("dashboard")) {
            Assert.fail("Login successful");
        } else {
            Assert.assertTrue(true);
        }
    }

    @AfterMethod
    public void tearDown() {
        driver.quit(); // Close browser
    }
}
