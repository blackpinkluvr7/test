package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class addCompanypage {
    private WebDriver driver;
    private WebDriverWait wait;

    public CompanyPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void goToAddCompanyPage() {
        driver.get("https://dev.popipro.com/control/manage-company");
    }

    public void enterAdminName(String adminName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='admin_name']")))
                .sendKeys(adminName);
    }

    public void enterEmail(String email) {
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
    }

    public void confirmPassword(String password) {
        driver.findElement(By.xpath("//input[@name='password_confirmation']")).sendKeys(password);
    }

    public void selectCurrency(String value) {
        Select dropdown = new Select(driver.findElement(By.xpath("//select[@name='currency']")));
        dropdown.selectByValue(value);
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
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'toastify')]")
        )).getText();
    }
}
}
