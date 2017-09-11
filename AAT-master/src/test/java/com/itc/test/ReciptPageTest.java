package com.itc.test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.itc.pages.EditExpensePage;
import com.itc.pages.ReciptPage;
import com.itc.util.BaseTestObject;

public class ReciptPageTest extends BaseTestObject{

	ReciptPage objReciptPage = null;
	EditExpensePage objEditExpensePage = null;
	LoginPageTest login=new LoginPageTest();
	

	@Parameters({"browserType"})
	@Test(priority=1, enabled=true,groups="SanityTest")
	public void verifyuploadRecipt() throws Exception
	{
	try 
	{
		//login.verifyLoginPage();
		objReciptPage = new ReciptPage(driver);
		objReciptPage.clickOnLisRecipt();
	    objReciptPage.uploadReciptUsingAutoIt();
	    objReciptPage.isResultPageDisplayed();
	    objReciptPage.isTitleDisplayed();
	    
	} 
	
	catch (Exception e) 
	{
		e.printStackTrace();
	}
	}
	@Test(priority = 2, enabled = true, groups = "SanityTest")
	public void clearData() throws Exception {
		try {
			objEditExpensePage = new EditExpensePage(driver);
			Thread.sleep(6000);
			objEditExpensePage.clickOnListExpense();
			objEditExpensePage.deleteExpense();
			objEditExpensePage.isExpenseDeleted();
			objEditExpensePage.isTitleDisplayed();
			objEditExpensePage.vendorDeletion1();
			objEditExpensePage.vendorDeletion2();
			

		}

		catch (Exception e) {
			throw new Exception("FAILED CLICK ON SITELOGO AND VERFIY PAGETITLE TESTCASE"
					+ "\n clickOnSiteLogoAndCheckThePageTitle" + e.getLocalizedMessage());
		}

	}
	@Test(priority = 3, enabled = true, groups = "SanityTest")
	public void logOut() throws Exception {
		try {
			objReciptPage = new ReciptPage(driver);
			objReciptPage.logout();
		}

		catch (Exception e) {
			throw new Exception("FAILED CLICK ON SITELOGO AND VERFIY PAGETITLE TESTCASE"
					+ "\n clickOnSiteLogoAndCheckThePageTitle" + e.getLocalizedMessage());
		}

	}
	}
	
	

	
	

