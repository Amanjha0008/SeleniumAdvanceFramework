package pages;

import helper.BrowserFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Account {
    WebDriver driver;
    WebDriverWait wait;


    @FindBy(how = How.XPATH, using = "//a[text()='Open New Account']")
    private WebElement openAccount;

    @FindBy(how = How.ID, using = "type")
    private WebElement dropdownType;

    @FindBy(how = How.ID, using = "fromAccountId")
    private WebElement dropdownAmount;

    @FindBy(how = How.CSS, using = "input[type='submit']")
    private WebElement openAcntBtn;




    public Account(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this); // Initialize WebElements
    }

    private static final
    Logger logger = Logger.getLogger(Account.class.getName());

    //Test Case 1: Open Saving Account
    public void setOpenAccount() throws InterruptedException {
        WebElement openAccountPara = wait.until(ExpectedConditions.visibilityOf(openAccount)); // Waiting for the visibility
        Actions actions = new Actions(driver);
        actions.moveToElement(openAccountPara).click().perform();

        Select Account_dropdown = new Select(dropdownType);
        Account_dropdown.selectByValue("1");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait for up to 10 seconds
        wait.until(ExpectedConditions.elementToBeClickable(dropdownAmount));

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            // Handle interruption if needed
            e.printStackTrace();
        }
        openAcntBtn.click();

        logger.info("Saving Account Successfully Run");

    }
    //Test Case 1: Open checking Account
    public void checkingOpenAccount() throws InterruptedException {
        WebElement openAccountPara = wait.until(ExpectedConditions.visibilityOf(openAccount)); // Waiting for the visibility
        Actions actions = new Actions(driver);
        actions.moveToElement(openAccountPara).click().perform();

        Select Account_dropdown = new Select(dropdownType);
        Account_dropdown.selectByValue("0");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait for up to 10 seconds
        wait.until(ExpectedConditions.elementToBeClickable(dropdownAmount));


        openAcntBtn.click();

        logger.info("Saving Account Successfully Run");

    }

}
