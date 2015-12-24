package com.softserve.edu.oms.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.pages.LogPage;

public class LogTest {
	private SoftAssert softAssert = new SoftAssert();

	@DataProvider
	public Object[][] invalidUsers() {
		return new Object[][] { { UserRepository.get().getAdminUser() }, };
	}

	@Test(dataProvider = "invalidUsers")
	public void checkInvalidLogin(IUser user) throws InterruptedException {
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://ssu-oms:8180/OMS/login.htm");
		// Test Operation
		// softAssert.assertTrue(false);
		LogPage logPage = new LogPage(driver);
		// LogPage logPage = PageFactory.initElements(driver, LogPage.class);
		Thread.sleep(2000);
		logPage.setLogin(user.getLogin());
		Thread.sleep(2000);
		logPage.setPassword(user.getPassword());
		Thread.sleep(2000);
		logPage.clickSubmit();
		// Check
		System.out.println("\t***Checking ...");
		// Assert.assertEquals(1, 2);
		softAssert.assertEquals(1, 2);
		System.out.println("\t***Check 1 == 2");
		// Assert.assertEquals(1, 1);
		softAssert.assertEquals(1, 1);
		System.out.println("\t***Check 1 == 1");
		// Return
		System.out.println("\t***QUIT");
		// driver.quit();
		softAssert.assertAll();
	}

}
