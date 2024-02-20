package base;

import helper.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;

import static helper.BrowserFactory.driver;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class TestFlow {

    private static final
    Logger logger = Logger.getLogger(BrowserFactory.class.getName());


    @BeforeTest
    public void createAccount() {
        // Launch the browser
        driver = BrowserFactory.startBrowser("chrome", "https://parabank.parasoft.com");
        String page_title = driver.getTitle();

        // Printing the title of the Page
        logger.info("Page Title: " + page_title);

        // Create a new account
        CreateAccount createAccount = PageFactory.initElements(driver, CreateAccount.class);
        createAccount.createAccount("virat", "mishra", "mandhana", "kanpur", "UP", "586665", "9876565655", "1234", "ViratMishra", "Virat@1234", "Virat@1234");

    }
    @Test(priority = 0)
    public void loginAccount(){
        CreateAccount loginAccountParaBank  = PageFactory.initElements(driver, CreateAccount.class);
        loginAccountParaBank.loginAccount("ViratMishra", "Virat@1234");
    }
    @Test(priority = 1)
    public void openAccount() throws InterruptedException {
        Account account = PageFactory.initElements(driver, Account.class);
        account.setOpenAccount();
        account.checkingOpenAccount();
    }

    @Test(priority = 2)
    public void transferFunds() throws InterruptedException {
        TransferFunds transfer = PageFactory.initElements(driver, TransferFunds.class);
        driver.navigate().refresh();
        transfer.transferFunds();
        transfer.transferToAccount("200");
        transfer.transferToAccount("352");
        transfer.transferToAccount("778");
        transfer.transferToAccount("9022");
        transfer.transferToAccount("10000");
        transfer.undefinedTransaction("200");
    }

    @Test(priority = 3)
    public void billPay() throws InterruptedException {
        BillPay billPay = PageFactory.initElements(driver, BillPay.class);
        billPay.billPayFromAccount("Virat Mishra", "Noida", "Noida", "UP", "432326", "+5548954548","1000","1000","800");
        billPay.billPayDifferentAccount("Virat Mishra", "Noida", "Noida", "UP", "432326", "+5548954548","2000","2000","900");
    }
    @Test(priority = 4)
    public void requestLoan() throws InterruptedException {
        RequestLoan loan= PageFactory.initElements(driver, RequestLoan.class);
        loan.loanRequest("9000", "900");
        loan.loanRequest("200", "2000");
        loan.loanRequestEmpty("", "");
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
