package com.softserve.edu.oms.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class UserInfoPage {
	protected WebDriver driver;
	//
	private WebElement firstname;
	private WebElement lastname;
	private WebElement role;
	private WebElement logout;

	public UserInfoPage(WebDriver driver) {
		this.driver = driver;
		//
		this.firstname=driver.findElement(By.xpath("//td[text()='First name']/following-sibling::td"));
		this.lastname=driver.findElement(By.xpath("//td[text()='Last name']/following-sibling::td"));
		this.role=driver.findElement(By.xpath("//td[text()='Role']/following-sibling::td"));
		this.logout=driver.findElement(By.xpath("//a[@href='/OMS/logout.htm']"));
		//this.logout=driver.findElement(By.cssSelector(".spec img"));
	}
	
	// Get Elements
	public WebElement getFirstname() {
		return this.firstname;
	}
	
	public WebElement getLastname() {
		return this.lastname;
	}

	public WebElement getRole() {
		return this.role;
	}
	
	public WebElement getLogout() {
		return this.logout;
	}
	
	public String getFirstnameText() {
		return this.firstname.getText();
	}
	
	public String getLastnameText() {
		return this.lastname.getText();
	}

	public String getRoleText() {
		return this.role.getText();
	}

	// Set Data
	public void clickLogout() {
		this.logout.click();
	}
	
	// Business Logic
	public LoginPage gotoLogout() {
		clickLogout();
		return new LoginPage(driver);
	}

}
