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

import com.softserve.edu.dao.DataSource;
import com.softserve.edu.oms.data.DataSourceRepository;
import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.UrlRepository.SsuUrls;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.pages.AdminHomePage;
import com.softserve.edu.oms.pages.LoginPage;
import com.softserve.edu.service.UserService;

public class LoginDBTest {
	//
	private WebDriver driver;

	@BeforeClass
	public void oneTimeSetUp() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@AfterClass
	public void oneTimeTearDown() {
		driver.quit();
	}

	@BeforeMethod
	public void setUp() {
		driver.get(SsuUrls.LOGIN.toString());
	}

	@AfterMethod
	public void tearDown() {
		driver.get(SsuUrls.LOGOUT.toString());
	}

	@DataProvider
	public Object[][] newUsers() {
		return new Object[][] { { UserRepository.get().getNewUser(),
					DataSourceRepository.getJtdsMsSqlRemote() }, };
	}

	@Test(dataProvider = "newUsers")
	public void checkAdminLogin(IUser newUser, DataSource dataSource) throws InterruptedException {
		// Precondition
		UserService.get(dataSource).insertUser(newUser);
		Thread.sleep(2000);
		// Test Operation
		AdminHomePage adminHomePage = new LoginPage(driver)
				.successAdminLogin(newUser);
		// Check
		Assert.assertEquals(adminHomePage.getFirstnameText(),
				newUser.getFirstname());
		Assert.assertEquals(adminHomePage.getLastnameText(),
				newUser.getLastname());
		Assert.assertEquals(adminHomePage.getLastnameText(),
				newUser.getLastname());
		Thread.sleep(2000);
		// Restore DB
		UserService.get().deleteUser(newUser);
	}

}
