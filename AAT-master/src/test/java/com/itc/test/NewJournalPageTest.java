package com.itc.test;

import org.testng.annotations.Test;

import com.itc.pages.NewJournalPage;
import com.itc.util.BaseTestObject;
import com.itc.util.ExcelutilObject;

public class NewJournalPageTest extends BaseTestObject {

	NewJournalPage objNewJournalPage = null;
	LoginPageTest login = new LoginPageTest();
	boolean flag = false;
	boolean flag1 = false;
	boolean titleflag = false;
	public static String excelPath = System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\testDataSheet.xlsx";

	//@Parameters({ "browserType" })
	//@Test(priority = 0, enabled = true,groups="Regression")
	public void Login() throws Exception {
		login.verifyLoginPage();

	}


	@Test(priority = 0, enabled = true,groups="Regression")
	public void verifyJournalCreation() throws Exception {
		try {
			objNewJournalPage = new NewJournalPage(driver);
			objNewJournalPage.clickOnNewJournal();
			String debit =getValFromExcel(1,2);
		    String credit =getValFromExcel(1,3);
		    String notes=getValFromExcel(1,4);
			objNewJournalPage.newJournalCreation(4,debit,credit,notes);
			flag1 = objNewJournalPage.isResultPageDisplayed();
			//Assert.assertTrue(flag1);

		}

		catch (Exception e) {
			throw new Exception("FAILED CLICK ON SITELOGO AND VERFIY PAGETITLE TESTCASE"
					+ "\n clickOnSiteLogoAndCheckThePageTitle" + e.getLocalizedMessage());
		}

	}

	@Test(priority = 1, enabled = true,groups="Regression")
	public void verifyJournalCreation2() throws Exception {
		try {
			objNewJournalPage = new NewJournalPage(driver);
			objNewJournalPage.clickOnNewJournal();
			String damount =getValFromExcel(2,2);
		    String camount =getValFromExcel(2,3);
		    String notes=getValFromExcel(2,4);
			objNewJournalPage.newJournalCreation(7,damount,camount,notes);
			flag1 = objNewJournalPage.isResultPageDisplayed();
			//Assert.assertTrue(flag1);

		}

		catch (Exception e) {
			throw new Exception("FAILED CLICK ON SITELOGO AND VERFIY PAGETITLE TESTCASE"
					+ "\n clickOnSiteLogoAndCheckThePageTitle" + e.getLocalizedMessage());
		}

	}

public static String getValFromExcel(int row,int col) throws Exception{
	
	ExcelutilObject.setExcelFile(excelPath, "Journal");
	return ExcelutilObject.getCellData(row, col);
}
}