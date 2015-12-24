package com.softserve.edu.oms.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogPage {
	private WebDriver driver;
	//
	@FindBy(name = "j_username")
	//@CacheLookup
	private WebElement login;
	//
	@FindBy(name = "j_password")
	@CacheLookup
	private WebElement password;
	//
	@FindBy(name = "submit")
	private WebElement submit;

	public LogPage(WebDriver driver) {
		this.driver = driver;
		//
		PageFactory.initElements(driver, this);
	}

	public void setLogin(String login) throws InterruptedException {
		this.login.sendKeys(login);
		System.out.println("\t+++Refresh");
		Thread.sleep(1000);
		driver.navigate().refresh();
		Thread.sleep(1000);
		this.login.sendKeys(login);
		Thread.sleep(1000);
	}

	public void setPassword(String password) {
		this.password.sendKeys(password);
	}

	public void clickSubmit() {
		this.submit.click();
	}

}
