package com.softserve.edu.oms.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.UrlRepository.SsuUrls;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.pages.HomePage;
import com.softserve.edu.oms.pages.LoginPage;

public class LoginParallelTest {

	@DataProvider(parallel = true)
	public Object[][] allUsers() {
		return new Object[][] {
				{ UserRepository.get().getAdminUser() },
				{ UserRepository.get().getCustomerUsers() }
				};
	}

	@Test(dataProvider = "allUsers")
	public void checkLogin(IUser user) throws InterruptedException {
		//SoftAssert softAssert = new SoftAssert();
		// Precondition
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//driver.get("http://ssu-oms:8180/OMS/login.htm");
		driver.get(SsuUrls.LOGIN.toString());
		System.out.println("\t***Running, Thread Id = "
				+ Thread.currentThread().getId() + "  User.login name = "
				+ user.getLogin());
		Thread.sleep(2000);
		// Test Operation
		HomePage homePage = new LoginPage(driver).successLogin(user);
		// Check
		// Use softAssert
		Assert.assertEquals(homePage.getFirstnameText(), user.getFirstname());
		Assert.assertEquals(homePage.getLastnameText(), user.getLastname());
		Assert.assertEquals(homePage.getLastnameText(), user.getLastname());
		Thread.sleep(2000);
		// Return
		homePage.gotoLogout();
		// LoginStartPage.quit();
		// driver.quit();
		//softAssert.assertAll();
	}

}
