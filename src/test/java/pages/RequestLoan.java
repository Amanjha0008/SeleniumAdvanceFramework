package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.swing.*;
import java.time.Duration;
import java.util.logging.Logger;

public class RequestLoan {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(how = How.XPATH, using = "//a[text()='Request Loan']")
    private WebElement requestLink;

    @FindBy(how = How.ID, using = "amount")
    private WebElement amountInput;

    @FindBy(how = How.ID, using = "downPayment")
    private WebElement downPaymentInput;

    @FindBy(how = How.ID, using = "fromAccountId")
    private WebElement fromAccountDropdown;

    @FindBy(how = How.CSS, using = "input.button[value='Apply Now']")
    private WebElement applyNowButton;

    @FindBy(how = How.XPATH, using = "//p[contains(@class, 'error') and contains(text(), 'An internal error has occurred and has been logged.')]")
    private WebElement requestError;

    @FindBy(how = How.CSS, using = "input.button[value='Send Payment']")
    private WebElement sendPaymentBtn;

    public RequestLoan(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this); // Initialize WebElements
    }

    private static final
    Logger logger = Logger.getLogger(RequestLoan.class.getName());
    //Test Case 1: Loan Request with given inputs
    public void loanRequest(String amount, String downPayment){
        WebElement requestLoanLink = wait.until(ExpectedConditions.elementToBeClickable(requestLink));
        requestLoanLink.click();
        amountInput.sendKeys(amount);
        downPaymentInput.sendKeys(downPayment);

        Select fromAccountDropdownSelect = new Select(fromAccountDropdown);
        fromAccountDropdownSelect.selectByValue("22113");
        applyNowButton.click();

        logger.info("Request Loan Successfully Executed");
    }
    // Test Case 2: Loan Request with empty inputs
    public void loanRequestEmpty(String amount, String downPayment){
        WebElement requestLoanLink = wait.until(ExpectedConditions.elementToBeClickable(requestLink));
        requestLoanLink.click();
        amountInput.sendKeys(amount);
        downPaymentInput.sendKeys(downPayment);
        boolean iselementDisplay = elementPresent(requestError);
        Assert.assertFalse(iselementDisplay, "Error message not present for empty fields");

        applyNowButton.click();

    }
    public boolean elementPresent(WebElement element){
        try{
            element.isDisplayed();
            return true;
        } catch (NoSuchElementException | StaleElementReferenceException e){
            return false;
        }
    }

}
