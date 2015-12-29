package com.softserve.edu.oms.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;

import com.softserve.edu.oms.data.IUser;
import com.softserve.edu.oms.tests.LoginTest;

public class LoginPage {
	public static final Logger logger = LoggerFactory.getLogger(LoginPage.class);
	//
	protected WebDriver driver;
	//
	private WebElement login;
	private WebElement password;
	private WebElement submit;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		//
		this.login = driver.findElement(By.name("j_username"));
		this.password = driver.findElement(By.name("j_password"));
		this.submit = driver.findElement(By.name("submit"));
		logger.info("LoginPage Created");
		Reporter.log("<br>LoginPage <font color='green'>Created</font>");
	}

	// Get Elements

	public WebElement getLogin() {
		return this.login;
	}

	public WebElement getPassword() {
		return this.password;
	}

	public WebElement getSubmit() {
		return this.submit;
	}

	public String getLoginText() {
		return this.login.getText();
	}

	public String getPasswordText() {
		return this.password.getText();
	}

	// Set Data

	public void setLogin(String login) {
		this.login.sendKeys(login);
	}

	public void setPassword(String password) {
		this.password.sendKeys(password);
	}

	public void clearLogin() {
		this.login.clear();
	}

	public void clearPassword() {
		this.password.clear();
	}

	public void clickLogin() {
		this.login.click();
	}

	public void clickPassword() {
		this.password.click();
	}

	public void clickSubmit() {
		this.submit.click();
	}

	// Business Logic

	private void setLoginData(IUser user) {
		clickLogin();
		clearLogin();
		setLogin(user.getLogin());
		clickPassword();
		clearPassword();
		setPassword(user.getPassword());
		clickSubmit();
	}

	public HomePage successLogin(IUser user) {
		setLoginData(user);
		// Return a new page object representing the destination.
		return new HomePage(driver);
	}

	public AdminHomePage successAdminLogin(IUser admin) {
		setLoginData(admin);
		// Return a new page object representing the destination.
		return new AdminHomePage(driver);
	}

	public CustomerHomePage successCustomerLogin(IUser customer) {
		setLoginData(customer);
		// Return a new page object representing the destination.
		return new CustomerHomePage(driver);
	}

	public LoginValidatorPage unsuccessfulLogin(IUser invalidUser) {
		setLoginData(invalidUser);
		return new LoginValidatorPage(driver); // return this;
	}

}
