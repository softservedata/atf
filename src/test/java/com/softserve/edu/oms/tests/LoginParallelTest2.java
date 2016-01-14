package com.softserve.edu.oms.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.data.ListUtils;
import com.softserve.edu.oms.data.UserRepository;
import com.softserve.edu.oms.pages.HomePage;
import com.softserve.edu.oms.pages.LoginStartPage;

public class LoginParallelTest2 {

	@AfterClass
	public void oneTimeTearDown() {
		LoginStartPage.quit();
	}

	@DataProvider(parallel = true)
	public Object[][] allUsers() {
		return new Object[][] {
				{ UserRepository.get().getAdminUser() },
				{ UserRepository.get().getCustomerUsers() }
				};
	}

	@DataProvider//(parallel = true)
	public Object[][] allUsersCSV() {
		return (new ListUtils()).toMultiArray(UserRepository.getAllUsersCSV());
	}

	@DataProvider//(parallel = true)
	public Object[][] allUsersExcel() {
		return (new ListUtils()).toMultiArray(UserRepository.getAllUsersExcel());
	}

	@DataProvider//(parallel = true)
	public Object[][] allUsersDB() {
		return (new ListUtils()).toMultiArray(UserRepository.getAllUsersDB());
	}

	//@Test(dataProvider = "allUsers")
	//@Test(dataProvider = "allUsersCSV")
	//@Test(dataProvider = "allUsersExcel")
	@Test(dataProvider = "allUsersDB")
	public void checkLogin(IUser user) throws InterruptedException {
		// Precondition
		System.out.println("\t***Running, Thread Id = "
				+ Thread.currentThread().getId() + "  User.login name = "
				+ user.getLogin());
		// Test Operation
		HomePage homePage = LoginStartPage.load()
				.successLogin(user);
		// Check
		Assert.assertEquals(homePage.getFirstnameText(), user.getFirstname());
		Assert.assertEquals(homePage.getLastnameText(), user.getLastname());
		Thread.sleep(4000);
		// Return
		homePage.gotoLogout();
		// LoginStartPage.quit();
		// driver.quit();
	}

}
