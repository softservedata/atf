package com.softserve.edu.oms.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.UrlRepository.SsuUrls;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.pages.AdminHomePage;
import com.softserve.edu.oms.pages.CustomerHomePage;
import com.softserve.edu.oms.pages.LoginPage;
import com.softserve.edu.oms.pages.LoginValidatorPage;


public class LoginTest {
	private WebDriver driver;

	@BeforeClass
	public void oneTimeSetUp() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("@BeforeClass - oneTimeSetUp");
	}

	@AfterClass
	public void oneTimeTearDown() {
		driver.quit();
		//LoginStartPage.get().quit();	
	}

	@BeforeMethod
	public void setUp() {
		//driver.get("http://ssu-oms:8180/OMS/login.htm");
		driver.get(SsuUrls.LOGIN.toString());
	}

	@AfterMethod
	public void tearDown() {
		//driver.get("http://ssu-oms:8180/OMS/logout.htm");
		driver.get(SsuUrls.LOGOUT.toString());
		//LoginStartPage.get().logout();
	}

	@DataProvider
	public Object[][] invalidUsers() {
		return new Object[][] { 
				{ UserRepository.get().getInvalidUser() },
		};
	}

	@Test(dataProvider = "invalidUsers")
	public void checkInvalidLogin(IUser invalidUser) throws InterruptedException {
		// Test Operation
//		LoginValidatorPage loginValidatorPage = LoginStartPage.loadOne()
//				.unsuccessfulLogin(invalidUser);
		LoginValidatorPage loginValidatorPage = new LoginPage(driver)
			.unsuccessfulLogin(invalidUser);
		// Check
		Assert.assertTrue(loginValidatorPage.getValidatorText()
				.startsWith(LoginValidatorPage.START_VALIDATOR_MESSAGE));
		Thread.sleep(2000);
		// Return
		//LoginStartPage.quit();
	}

	@DataProvider
	public Object[][] adminUsers() {
		return new Object[][] {
				{ UserRepository.get().getAdminUser() },
		};
	}

	@Test(dataProvider = "adminUsers")
	public void checkAdminLogin(IUser adminUser) throws InterruptedException {
		// Test Operation
//		AdminHomePage adminHomePage = LoginStartPage.loadOne()
//				.successAdminLogin(adminUser);
		AdminHomePage adminHomePage = new LoginPage(driver)
			.successAdminLogin(adminUser);
		// Check
		Assert.assertEquals(adminHomePage.getFirstnameText(),
				adminUser.getFirstname());
		Assert.assertEquals(adminHomePage.getLastnameText(),
				adminUser.getLastname());
		Assert.assertEquals(adminHomePage.getLastnameText(),
				adminUser.getLastname());
		Thread.sleep(2000);
		// Return
		//adminHomePage.gotoLogout();
		//LoginStartPage.quit();
	}

	@DataProvider
	public Object[][] customerUsers() {
		return new Object[][] {
				//{ new User("logincustomer","logincustomer","logincustomer","qwerty","abcd@gmail.com","East","Customer") },
				{ UserRepository.get().getCustomerUsers() },
		};
	}

	@Test(dataProvider = "customerUsers")
	public void checkCustomerLogin(IUser customerUser) throws InterruptedException {
		// Test Operation
//		CustomerHomePage customerHomePage = LoginStartPage.loadOne()
//				.successCustomerLogin(customerUser);
		CustomerHomePage customerHomePage = new LoginPage(driver)
				.successCustomerLogin(customerUser);
		// Check
		Assert.assertEquals(customerHomePage.getFirstnameText(),
				customerUser.getFirstname());
		Assert.assertEquals(customerHomePage.getLastnameText(),
				customerUser.getLastname());
		Thread.sleep(2000);
		// Return
		//customerHomePage.gotoLogout();
		//LoginStartPage.quit();
	}

}
