package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.addCompanypage;

import java.time.Duration;
import java.util.UUID;

public class AddCompany {
    private WebDriver driver;
    private addCompanypage addCompanypage;
    private String uniqueEmail;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        addCompanypage = new addCompanypage(driver);
        driver.get("https://dev.popipro.com/control/login");
        addCompanypage.login("er.prafullgupta@gmail.com", "12345678");
        Thread.sleep(1000);
        new WebDriverWait(driver, Duration.ofSeconds(5));
        addCompanypage.getAddCompanyPage();
        uniqueEmail = "user" + UUID.randomUUID().toString().substring(0, 8) + "@gmail.com";
    }

    // ✅ Positive Test: Add a company with valid data
    @Test(priority = 1)
    public void addCompanyWithValidData() {
        addCompanypage.enterAdminName("Admin");
        addCompanypage.enterEmail(uniqueEmail);
        addCompanypage.enterPassword("12345678");
        addCompanypage.confirmPassword("12345678");
        addCompanypage.selectCurrency("1");
        addCompanypage.enterCompanyName("Company");
        addCompanypage.enterCompanyEmail(uniqueEmail);
        addCompanypage.enterCompanyDescription("This is an extra note");
        addCompanypage.clickSubmit();
        String actualMessage = addCompanypage.getToastMessage();
        String expectedMessage = "Company has been successfuly registered to popipro.";
        Assert.assertEquals(actualMessage, expectedMessage, "Success message mismatch!");
    }

    // ✅ Positive Test: Add a company with minimum data
    @Test(priority = 2)
    public void addCompanyWithMinimumData() {
        addCompanypage.enterAdminName("Admin");
        addCompanypage.enterEmail(uniqueEmail);
        addCompanypage.enterPassword("12345678");
        addCompanypage.confirmPassword("12345678");
        addCompanypage.selectCurrency("1");
        addCompanypage.enterCompanyName("Minimal Co");
        addCompanypage.enterCompanyEmail(uniqueEmail);
        addCompanypage.clickSubmit();
        String actualMessage = addCompanypage.getToastMessage();
        String expectedMessage = "Company has been successfuly registered to popipro.";
        Assert.assertEquals(actualMessage, expectedMessage, "Success message mismatch!");
    }

    // ❌ Negative Test: Add company with existing email
    @Test(priority = 3)
    public void addCompanyWithDuplicateEmail() {
        addCompanypage.enterAdminName("Admin");
        addCompanypage.enterEmail("user44@gmail.com");
        addCompanypage.enterPassword("12345678");
        addCompanypage.confirmPassword("12345678");
        addCompanypage.selectCurrency("1");
        addCompanypage.enterCompanyName("Company");
        addCompanypage.enterCompanyEmail("user44@gmail.com");
        addCompanypage.clickSubmit();
        String actualMessage = addCompanypage.getToastMessage();
        String expectedMessage = "The admin email has already been taken. (and 1 more error)";
        Assert.assertEquals(actualMessage, expectedMessage, "Duplicate email not handled!");
    }

    // ❌ Negative Test: Add company with invalid email format
    @Test(priority = 4)
    public void addCompanyWithInvalidEmailFormat() {
        addCompanypage.enterAdminName("Admin");
        addCompanypage.enterEmail("invalid-email");
        addCompanypage.enterPassword("12345678");
        addCompanypage.confirmPassword("12345678");
        addCompanypage.selectCurrency("1");
        addCompanypage.enterCompanyName("Company");
        addCompanypage.enterCompanyEmail("invalid-email");
        addCompanypage.clickSubmit();

        String actualMessage = addCompanypage.getToastMessage();
        String expectedMessage = "The admin email must be a valid email address. (and 1 more error)";
        Assert.assertEquals(actualMessage, expectedMessage, "Invalid email format not handled!");
    }

    // ❌ Negative Test: Add company with mismatched passwords
    @Test(priority = 5)
    public void addCompanyWithMismatchedPasswords() {
        addCompanypage.enterAdminName("Admin");
        addCompanypage.enterEmail(uniqueEmail);
        addCompanypage.enterPassword("12345678");
        addCompanypage.confirmPassword("87654321"); // Wrong confirmation
        addCompanypage.selectCurrency("1");
        addCompanypage.enterCompanyName("Company");
        addCompanypage.enterCompanyEmail(uniqueEmail);
        addCompanypage.clickSubmit();

        String actualMessage = addCompanypage.getToastMessage();
        String expectedMessage = "The password confirmation does not match.";
        Assert.assertEquals(actualMessage, expectedMessage, "Mismatched passwords not handled!");
    }

    // ❌ Negative Test: Missing required fields
    @Test(priority = 6)
    public void addCompanyWithMissingRequiredFields() {
        addCompanypage.clickSubmit();
        String actualMessage = addCompanypage.getToastMessage();
        String expectedMessage = "The admin name field is required. (and 5 more errors)";
        Assert.assertEquals(actualMessage, expectedMessage, "Missing fields not handled!");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit(); // Close browser after test
    }
}
