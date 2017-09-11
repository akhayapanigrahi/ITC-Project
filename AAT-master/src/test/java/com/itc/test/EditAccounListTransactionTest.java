package com.itc.test;

import org.testng.annotations.Test;

import com.itc.pages.EditAccountListTransaction;
import com.itc.util.BaseTestObject;
import com.itc.util.ExcelutilObject;


public class EditAccounListTransactionTest extends BaseTestObject{

	EditAccountListTransaction objEditAccountPage=null ;
	LoginPageTest login=new LoginPageTest();
	boolean flag = false;
	boolean flag1 = false;
	String titleflag=null;
	public static String excelPath = System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\testDataSheet.xlsx";

	//@Parameters({"browserType"})
	//@Test(priority=0,groups="Regression")
	public void Login() throws Exception{
		login.verifyLoginPage();
	}
	
	
	@Test(priority=0, enabled=true,groups="Regression")
	public void verifyEditAccount1() throws Exception
	{
	try 
	{
		objEditAccountPage= new EditAccountListTransaction(driver);
		objEditAccountPage.clickOnChartAccount();
		String vendorname =getValFromExcel(3,2);
	    String balance =getValFromExcel(3,3);
		//objEditVendorPage.clickOnVendorLink();
		objEditAccountPage.isTitleDisplayed();
		//Assert.assertTrue(title.contains("Edit Vendor:"));
		objEditAccountPage.clickOnAccountLink1(); 
		objEditAccountPage.editExpense(vendorname, balance);
	    objEditAccountPage.isResultPageDisplayed();
	   // Assert.assertTrue(flag1);
	        
	} 
	
	catch (Exception e) 
	{
		e.printStackTrace();
	}

	}
	@Test(priority=1, enabled=true,groups="Regression")
	public void verifyEditAccount2() throws Exception
	{
	try 
	{
		objEditAccountPage= new EditAccountListTransaction(driver);
		objEditAccountPage.clickOnChartAccount();
		String vendorname =getValFromExcel(4,2);
	    String balance =getValFromExcel(4,3);
		objEditAccountPage.isTitleDisplayed();
		objEditAccountPage.clickOnAccountLink2(); 
		objEditAccountPage.editExpense(vendorname, balance);
	    objEditAccountPage.isResultPageDisplayed();
	    //Assert.assertTrue(flag1);
	        
	} 
	
	catch (Exception e) 
	{
		e.printStackTrace();
	}

	}
	
public static String getValFromExcel(int row,int col) throws Exception{
		
		ExcelutilObject.setExcelFile(excelPath, "EditAccountData");
		return ExcelutilObject.getCellData(row, col);
	}


}
