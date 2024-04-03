package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public class BillPay {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(how = How.XPATH, using = "//a[text()='Bill Pay']")
    private WebElement billPaybtn;

    @FindBy(how = How.CSS, using = "input[name='payee.name']")
    private WebElement payeeNameInput;

    @FindBy(how = How.CSS, using = "input[name='payee.address.street']")
    private WebElement addressInput;

    @FindBy(how = How.CSS, using = "input[name='payee.address.city']")
    private WebElement cityInput;

    @FindBy(how = How.CSS, using = "input[name='payee.address.state']")
    private WebElement stateInput;

    @FindBy(how = How.CSS, using = "input[name='payee.address.zipCode']")
    private WebElement zipCodeInput;
    @FindBy(how = How.CSS, using = "input[name='payee.phoneNumber']")
    private WebElement phoneInput;

    @FindBy(how = How.CSS, using = "input[name='payee.accountNumber']")
    private WebElement toAccountInput;

    @FindBy(how = How.CSS, using = "input[name='verifyAccount']")
    private WebElement verifyToAccountInput;

    @FindBy(how = How.CSS, using = "input[name='amount']")
    private WebElement amountInput;

    @FindBy(how = How.CSS, using = "select[name='fromAccountId']")
    private WebElement accountDropdown;


    @FindBy(how = How.CSS, using = "input.button[value='Send Payment']")
    private WebElement sendPaymentBtn;

    private static final
    Logger logger = Logger.getLogger(BillPay.class.getName());


    public BillPay(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this); // Initialize WebElements
    }

    //Test case 1: Bill Pay by a account holder account
    public void billPayFromAccount(String payeeName,String address,String city, String state, String zipCode, String phone, String account, String verify, String amount){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        billPaybtn.click();
        payeeNameInput.sendKeys(payeeName);
        addressInput.sendKeys(address);
        cityInput.sendKeys(city);
        stateInput.sendKeys(state);
        zipCodeInput.sendKeys(zipCode);
        phoneInput.sendKeys(phone);
        toAccountInput.sendKeys(account);
        verifyToAccountInput.sendKeys(verify);
        amountInput.sendKeys(amount);

        Select accountDropdownSelect = new Select(accountDropdown);
        accountDropdownSelect.selectByValue("21780");

        sendPaymentBtn.click();

        logger.info("Bill Pay Successfully Run");

    }
    //Test case 2: Bill Pay by a different account
    public void billPayDifferentAccount(String payeeName,String address,String city, String state, String zipCode, String phone, String account, String verify, String amount){
        billPaybtn.click();
        payeeNameInput.sendKeys(payeeName);
        addressInput.sendKeys(address);
        cityInput.sendKeys(city);
        stateInput.sendKeys(state);
        zipCodeInput.sendKeys(zipCode);
        phoneInput.sendKeys(phone);
        toAccountInput.sendKeys(account);
        verifyToAccountInput.sendKeys(verify);
        amountInput.sendKeys(amount);

        Select accountDropdownSelect = new Select(accountDropdown);
        accountDropdownSelect.selectByValue("22002");

        sendPaymentBtn.click();

        logger.info("Bill pay Successfully Run");

    }


}
