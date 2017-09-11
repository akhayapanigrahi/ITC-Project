package com.itc.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.itc.common.AynaxUtil;
import com.itc.util.BasePageObject;

public class LoginPage extends BasePageObject {
	LoginPage objLoginPage = null;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	boolean flag = false;
	boolean flag1 = false;
	public static Logger logger = Logger.getLogger(LoginPage.class);


	/* Web elements */

	//By msgLoginPage = By.xpath("//span[contains(text(),' Login To Your Aynax Account')]");
	By msgLandingPage = By.xpath("//h1[contains(text(),'List of  Invoices')]");
	By TimeSheetTab = By.xpath("//a[contains(text(),'Timesheets')]");
	By loginTab = By.xpath("//a[@href='https://www.aynax.com/login.php']");
	By userEmailAddr = By.name("emailaddress");
	By userPwd = By.name("password");
	By titleText = By.className("loginLabel");
	By login = By.linkText("Login");
	By LoginBtn = By.name("submit");
	By homePageTitle = By.xpath("//h1[contains(text(),'List of  Invoices')]");

	public boolean isLoginPageDisplayed() throws Exception {
		try {
			flag = isElementPresent(login);
			if (flag) {
				logger.info("Login Page is displayed");
			} else {
				logger.info("Login Page is not displayed");

			}
		} catch (Exception e) {
			throw new Exception("Login page is displayed::" + isLoginPageDisplayed() + e.getLocalizedMessage());
		}
		return flag;
	}

	public void enterEmailAddr(String username) throws Exception {
		if (isElementPresent(userEmailAddr)) {
			setElement(userEmailAddr).sendKeys(username);
			Assert.assertTrue(isElementPresent(userEmailAddr), "Email area is not displayed");

		}
	}

	public void enterPassword(String Password) throws Exception {
		if (isElementPresent(userPwd)) {
			setElement(userPwd).sendKeys(Password);
			Assert.assertTrue(isElementPresent(userPwd), "Passowrd field is not displayed");

		}

	}

	public void clickLogin() throws Exception {
		if (isElementPresent(LoginBtn)) {
			setElement(LoginBtn).click();
		}
	}

	public void LoginAsUser(String userName, String password) throws Exception {
		try {
			enterEmailAddr(userName);
			enterPassword(password);
			clickLogin();
			waitForAnElement(TimeSheetTab, 20);

		} catch (Exception e) {
			throw new Exception("Login link is not present in Home page::");
		}
	}

	public LoginPage clickOnLogin() throws Exception {
		try {
			flag = isElementPresent(login);
			Assert.assertTrue(flag, "Login link is not displayed");
			setElement(login).click();
			AynaxUtil.explicitWait(3000);

		} catch (Exception e) {
			throw new Exception("Login link is not present in Home page::");
		}
		return new LoginPage(driver);
	}

	public boolean isLandingPageDisplayed() throws Exception {
		try {

			flag1 = isElementPresent(TimeSheetTab);
			if (flag1) {
				logger.info("User has successfully logged in and Expense page is displayed");
			} else {
				logger.info("Login not successfull and Home page is displayed");
			}
		} catch (Exception e) {
			throw new Exception("Login page is displayed::" + isLoginPageDisplayed() + e.getLocalizedMessage());
		}
		return flag1;
	}
}
