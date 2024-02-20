package pages;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.logging.Logger;

public class CreateAccount
{
    WebDriver driver;
    public CreateAccount(WebDriver driver){
        this.driver = driver;
    }

    // Locator for the Create Account by using @FindBy
    @FindBy(how = How.XPATH, using = "//a[text()='Register']")
    private WebElement registerAccount;

    @FindBy(how = How.ID, using = "customer.firstName")
    private WebElement firstName;

    @FindBy(how = How.ID, using = "customer.lastName")
    private WebElement lastName;

    @FindBy(how = How.ID, using = "customer.address.street")
    private WebElement addressInput;

    @FindBy(how = How.ID, using = "customer.address.city")
    private WebElement cityInput;

    @FindBy(how = How.ID, using = "customer.address.state")
    private WebElement stateInput;

    @FindBy(how = How.ID, using = "customer.address.zipCode")
    private WebElement zipCodeInput;

    @FindBy(how = How.ID, using = "customer.phoneNumber")
    private WebElement phoneInput;

    @FindBy(how = How.ID, using = "customer.ssn")
    private WebElement ssnInput;

    @FindBy(how = How.ID, using = "customer.username")
    private WebElement userNameInput;

    @FindBy(how = How.ID, using = "customer.password")
    private WebElement passwordInput;

    @FindBy(how = How.ID, using = "repeatedPassword")
    private WebElement passwordConfirmation;

    @FindBy(how = How.CSS, using = "input[type='submit'].button[value='Register']")
    private WebElement createBtn;

    @FindBy(how = How.CSS, using = ".login input[name='username']")
    private WebElement usernameInput;

    @FindBy(how = How.CSS, using = ".login input[name='password']")
    private WebElement passInput;

    @FindBy(how = How.CSS, using = ".login input[type='submit']")
    private WebElement loginButton;
    private static final
    Logger logger = Logger.getLogger(CreateAccount.class.getName());


    public void createAccount(String fname, String lname, String address, String city, String state, String zipCode, String phone, String ssn, String username, String password, String conf_pass )
    {
        registerAccount.click();
        firstName.sendKeys(fname);
        lastName.sendKeys(lname);
        addressInput.sendKeys(address);
        cityInput.sendKeys(city);
        stateInput.sendKeys(state);
        zipCodeInput.sendKeys(zipCode);
        phoneInput.sendKeys(phone);
        ssnInput.sendKeys(ssn);
        userNameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        passwordConfirmation.sendKeys(conf_pass);
        createBtn.click();
    }
    public void loginAccount(String username , String password){
        usernameInput.sendKeys(username);
        passInput.sendKeys(password);
        loginButton.click();

        logger.info("Create Account Successfully Run");
    }


}
