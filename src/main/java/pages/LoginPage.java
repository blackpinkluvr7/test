package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;


    private By usernameField = By.xpath("//input[@name='email']");
    private By passwordField = By.xpath("//input[@name='password']");
    private By loginButton = By.xpath("//button[@type='submit']");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();

    }
    public void getAddCompanyPage() {
        driver.get("https://dev.popipro.com/control/manage-company");
    }
    public void enterAdminName(String adminName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='admin_name']")))
                .sendKeys(adminName);

    }

    public void enterEmail(String email) {
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
    }

    public void enterPasswordd(String password) {
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
    }

    public void confirmPassword(String password) {
        driver.findElement(By.xpath("//input[@name='password_confirmation']")).sendKeys(password);
    }

    public void selectCurrency(String value) {
        Select dropdown = new Select(driver.findElement(By.xpath("//select[@name='currency']")));
        dropdown.selectByValue(value);
    }

    public void goToAddCompanyPage() {
        driver.get("https://dev.popipro.com/control/manage-company");
    }

    public void enterCompanyName(String companyName) {
        driver.findElement(By.xpath("//input[@name='company_name']")).sendKeys(companyName);
    }
    public void enterCompanyEmail(String companyEmail) {
        driver.findElement(By.xpath("//input[@name='company_name']/following::input[@type='email']")).sendKeys(companyEmail);
    }

    public void enterCompanyDescription(String description) {
        driver.findElement(By.xpath("//div[@role='textbox']")).sendKeys(description);
    }

    public void clickSubmit() {
        driver.findElement(By.xpath("//button[contains(@class,'go-to-next')]")).click();
    }
    public String getToastMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'toastify')]")
        )).getText();
    }

    public void adminDetails() {
        enterAdminName("Test CompanyAuto");
        enterEmail("emailtest@gmail.com");
        enterPassword("12345678");
        confirmPassword("12345678");
        selectCurrency("1");
        enterCompanyName("Test CompanyAuto");
        enterCompanyDescription("Test CompanyAuto");
        clickSubmit();
    }

}
