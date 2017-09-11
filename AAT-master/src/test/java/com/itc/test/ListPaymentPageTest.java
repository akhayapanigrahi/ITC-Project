package com.itc.test;

import org.testng.annotations.Test;

import com.itc.pages.ListPaymentPage;
import com.itc.util.BaseTestObject;

public class ListPaymentPageTest extends BaseTestObject {

	ListPaymentPage objListPaymentPage = null;
	LoginPageTest login = new LoginPageTest();
	boolean flag1=false;
	boolean titleflag=false;
	
	//@Parameters({ "browserType"})
	@Test(priority = 0, enabled = true,groups="Regression")
	public void verifyNewExpensePageDisplay() throws Exception {
		try {
			//login.verifyLoginPage();
			objListPaymentPage = new ListPaymentPage(driver);
			objListPaymentPage.clickOnListPayment();
			flag1=objListPaymentPage.isResultPageDisplayed();
		    //Assert.assertTrue(flag1, "List Payment page is not displayed");

		     objListPaymentPage.isTitleDisplayed();
		   // Assert.assertTrue(titleflag, "List Payment page is not displayed");
		}

		catch (Exception e) {
			throw new Exception("FAILED CLICK ON SITELOGO AND VERFIY PAGETITLE TESTCASE"
					+ "\n clickOnSiteLogoAndCheckThePageTitle" + e.getLocalizedMessage());

		}
	}
}
