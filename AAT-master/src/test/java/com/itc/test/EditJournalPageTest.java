package com.itc.test;

import org.testng.annotations.Test;

import com.itc.pages.EditJournalPage;
import com.itc.util.BaseTestObject;
import com.itc.util.ExcelutilObject;

public class EditJournalPageTest extends BaseTestObject {

	EditJournalPage objEditJournalPage = null;
	LoginPageTest login = new LoginPageTest();
	boolean flag = false;
	boolean flag1 = false;
	boolean titleflag = false;
	public static String excelPath = System.getProperty("user.dir")
			+ "\\src\\test\\resources\\testdata\\testDataSheet.xlsx";

	//@Parameters({ "browserType"})
	//@Test(priority = 0, enabled = true,groups="Regression")
	public void Login() throws Exception {
		login.verifyLoginPage();

	}

	@Test(priority = 0, enabled = true,groups="Regression")
	public void verifyJournalEdit() throws Exception {
		try {
			String debit = getValFromExcel(1, 2);
			String credit = getValFromExcel(1, 3);
			String notestext = getValFromExcel(1, 4);
			objEditJournalPage = new EditJournalPage(driver);
			objEditJournalPage.clickOnListJournal();
			objEditJournalPage.clickOnJournal();
			objEditJournalPage.editJournalAndSave(4, debit, credit, notestext);
			objEditJournalPage.isResultPageDisplayed();

		} catch (Exception e) {
			e.printStackTrace();
			/*throw new Exception("FAILED CLICK ON Journal AND VERFIY edit Journal TESTCASE"
					+ "\n clickOnSiteLogoAndCheckThePageTitle" + e.getLocalizedMessage());*/
		}

	}

	@Test(priority = 1, enabled = true,groups="Regression")
	public void verifyJournalEntryDelete() throws Exception {
		try {
			objEditJournalPage = new EditJournalPage(driver);
			objEditJournalPage.clickOnListJournal();
			objEditJournalPage.clickOnJournal();
			objEditJournalPage.clickOnDeleteEntry();
			objEditJournalPage.isResultPageDisplayed();
		}

		catch (Exception e) {
			throw new Exception("FAILED CLICK ON SITELOGO AND VERFIY PAGETITLE TESTCASE"
					+ "\n clickOnSiteLogoAndCheckThePageTitle" + e.getLocalizedMessage());
		}

	}

	public static String getValFromExcel(int row, int col) throws Exception {

		ExcelutilObject.setExcelFile(excelPath, "Journal");
		return ExcelutilObject.getCellData(row, col);
	}
}