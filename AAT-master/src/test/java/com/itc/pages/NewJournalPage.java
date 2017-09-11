package com.itc.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.itc.common.AynaxUtil;
import com.itc.util.BasePageObject;

public class NewJournalPage extends BasePageObject {

	public NewJournalPage(WebDriver driver) {
		super(driver);
	}

	boolean flag = false;
	boolean accountflag = false;
	boolean titleflag = false;
	boolean flag1 = false;
	public static Logger logger = Logger.getLogger(NewJournalPage.class);

	/* Web elements */

	By accountingTab = By.xpath(".//*[@id='top-menu']//a[contains(.,'Accounting')]");
	By newJournal = By.xpath(".//*[@id='top-menu']//a[contains(.,'New Journal Page')]");
	By date1 = By.id("date1");
	By date2 = By.id("date2");
	By accdropDown = By.id("account1");
	By debit = By.xpath(".//table[@id='transactionsTable']//td/input[@id='debit1']");
	By credit = By.xpath(".//table[@id='transactionsTable']//td/input[@id='credit2']");
	By notes = By.id("notes1");
	By startBal = By.name("p_balance");
	By saveButton = By.id("save");
	By successDialog = By.xpath("//div[@class='alert alert-success alert-dismissable']");
	By errorDialog = By.xpath("//div[@class='alert alert-danger alert-dismissable']");

	public void clickOnNewJournal() throws Exception {
		try {
			flag = isElementPresent(accountingTab);
			if (flag) {
				logger.info("New Accounting tab is displayed");
				WebElement accTab = driver.findElement(accountingTab);
				AynaxUtil.explicitWait(3000);
				WebElement journallink = driver.findElement(newJournal);
				Actions act = new Actions(driver);
				act.moveToElement(accTab).click(accTab).click(journallink).doubleClick().build().perform();
				AynaxUtil.explicitWait(3000);
				act.click().build().perform();
				Assert.assertTrue(flag, "Accounting link is not displayed");
				isTitleDisplayed();
				AynaxUtil.explicitWait(3000);

			} else {
				logger.info("New Accounting tab is not displayed");
			}

		} catch (Exception e) {
			e.printStackTrace();
			// throw new Exception("Expense tab is
			// displayed::"+isExpenseTabDisplayed()+e.getLocalizedMessage());
		}
	}

	public void enterDate() throws Exception {

		setElement(date1).click();
		setElement(date2).click();

	}

	public void selectAccount(int index) throws Exception {
		try {
			accountflag = isElementPresent(accdropDown);
			if (accountflag) {
				logger.info("Customer type dropdown is displayed");
				WebElement web = driver.findElement(By.id("account1"));
				Select sel = new Select(web);
				List<WebElement> lst = sel.getOptions();
				for (int i = 0; i < lst.size(); i++) {
					sel.selectByIndex(index);
				}
				Assert.assertTrue(accountflag, " Category dropdown is not displayed");

			} else {

				logger.info("New Journal tab is not displayed");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void enterDebitAmont(String damount) throws Exception {
		try {

			logger.info("Debit Amount textarea is displayed");
			setElement(debit).sendKeys(damount);
			// Assert.assertTrue(amountflag, " Amount is not displayed");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void enterCreditAmont(String camount) throws Exception {
		try {
			logger.info("Credit Amount textarea is displayed");
			setElement(credit).sendKeys(camount);
			// Assert.assertTrue(amountflag, " Amount is not displayed");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void enterNotes(String notes1) throws Exception {
		if (isElementPresent(notes)) {
			setElement(notes).sendKeys(notes1);

		}
	}

	public void clickSave() throws Exception {
		if (isElementPresent(saveButton)) {
			setElement(saveButton).click();
		}
	}

	public void newJournalCreation(int index, String damount, String camount, String notes1) throws Exception {
		try {
			enterDate();
			selectAccount(index);
			enterDebitAmont(damount);
			enterCreditAmont(camount);
			enterNotes(notes1);
			clickSave();

		} catch (Exception e) {
			throw new Exception("New Journal link is not present in Expense page::");
		}
		// return new LoginPage(driver);
	}

	public boolean isTitleDisplayed() {

		getPageTitle();
		return titleflag;
	}

	public boolean isResultPageDisplayed() throws Exception {
		try {

			flag1 = isElementPresent(successDialog);
			if (flag1) {
				logger.info("Success page of Journal creation is displayed");
				String expectedtitle = getPageTitle();
				logger.info("Title is "+expectedtitle);
				Assert.assertTrue(expectedtitle.contains("Edit Transaction::"));
				String text = getText(successDialog);
				logger.info("Success text"+text);
				Assert.assertTrue(
						text.contains("Journal page successfully saved!  Click here to record another transfer"));

			} else {

				logger.info("New Journal creation error page is displayed");
				String expectedtitle = getPageTitle();
				Assert.assertTrue(expectedtitle.contains("New Transaction:: Aynax.com"));
				String text = getText(errorDialog);
				logger.info("The error text is" + text);
				Assert.assertTrue(text.contains("Total debit and total credit must equal for each transaction date."));

			}
		} catch (Exception e) {
			throw new Exception("New Journal page is displayed::" + isResultPageDisplayed() + e.getLocalizedMessage());
		}
		return flag1;
	}

}
