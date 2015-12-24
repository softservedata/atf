package com.softserve.edu.oms.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminHomePage extends UserInfoPage {
	//
	private WebElement administration;

	public AdminHomePage(WebDriver driver) {
		super(driver);
		this.administration=driver.findElement(By.partialLinkText("Administration"));
	}

	// Get Elements
	public WebElement getAdministration() {
		return this.administration;
	}

	// Set Data
	public void clickAdministration() {
		this.administration.click();
	}
	
	// Business Logic
	public AdministrationPage gotoAdministration() {
		clickAdministration();
	    return new AdministrationPage(driver);
	}

}
