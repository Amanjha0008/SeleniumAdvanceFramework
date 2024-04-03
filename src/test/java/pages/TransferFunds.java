package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

public class TransferFunds {
    WebDriver driver;
    WebDriverWait wait;


    @FindBy(how = How.XPATH, using = "//a[text()='Transfer Funds']")
    private WebElement transferAccountBtn;

    @FindBy(how = How.ID, using = "amount")
    private WebElement tansferInput;

    @FindBy(how = How.ID, using = "fromAccountId")
    private WebElement fromDropdownAccount;

    @FindBy(how = How.ID, using = "toAccountId")
    private WebElement toDropdownAccount;

    @FindBy(how = How.CSS, using = "input[value='Transfer']")
    private WebElement transferbtn;


    public TransferFunds(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this); // Initialize WebElements
    }

    public void transferFunds() {
        transferAccountBtn.click();
        driver.navigate().refresh();
    }
    private static final
    Logger logger = Logger.getLogger(BillPay.class.getName());
    //Test case 1: Transfer Funds in accounts(amounts)
    public void transferToAccount(String amount) {
        WebElement transferInp = wait.until(ExpectedConditions.visibilityOf(tansferInput));
        transferInp.sendKeys(amount);

        Select dropdown = new Select(fromDropdownAccount);
        WebElement selectedFromAccountOption = dropdown.getOptions().get(0);
        String fromAccount = selectedFromAccountOption.getAttribute("value");

        Select toDropdownSelect = new Select(toDropdownAccount);
        WebElement selectedToAccountOption = toDropdownSelect.getOptions().get(0);
        String toAccount = selectedToAccountOption.getAttribute("value");
        transferbtn.click();

        writeDataToCSV(fromAccount, toAccount, amount);
    }

    //Test Case 2:Transfer Funds with undefined Account
    public void undefinedTransaction(String amount){
        transferAccountBtn.click();
        tansferInput.click();

        try {
            Select fromDropdown = new Select(fromDropdownAccount);
            fromDropdown.selectByValue("undefined");

            Select toDropdown = new Select(toDropdownAccount);
            toDropdown.selectByValue("undefined");

            transferbtn.click();
        } catch (Exception e) {
            logger.info("One of the dropdown options with value 'undefined' is not found.");
            e.printStackTrace();

        }

    }
    private void writeDataToCSV(String fromAccount, String toAccount, String amount) {
        try (FileWriter writer = new FileWriter("DataDriven/transferAmount.csv", true)) {
            // Append data to CSV file
            writer.append(String.join(",", fromAccount, toAccount, amount));
            writer.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
