package com.itc.test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.itc.common.AynaxConstants;
import com.itc.pages.LoginPage;
import com.itc.util.BaseTestObject;

import junit.framework.Assert;

public class LoginPageTest extends BaseTestObject {

	LoginPage objLoginPage = null;
	AynaxConstants obj = new AynaxConstants();
	boolean flag = false;
	boolean flag1 = false;
	public String username = ObjProperty.getProperty("userName");
	public String Password = ObjProperty.getProperty("password");

	@Parameters({ "browserType"})
	@Test(priority = 0, enabled = true,groups={"Regression"})
	public void verifyLoginPage() throws Exception {
		try {

			objLoginPage = new LoginPage(driver);
			flag = objLoginPage.isLoginPageDisplayed();
			objLoginPage = objLoginPage.clickOnLogin();
			objLoginPage.LoginAsUser(username, Password);
			flag1 = objLoginPage.isLandingPageDisplayed();
			Assert.assertTrue(flag1);
		}

		catch (Exception e) {
			throw new Exception("FAILED CLICK ON SITELOGO AND VERFIY PAGETITLE TESTCASE"
					+ "\n clickOnSiteLogoAndCheckThePageTitle" + e.getLocalizedMessage());
		}
	}
}
