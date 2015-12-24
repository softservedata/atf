package com.softserve.edu.oms.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CustomerHomePage extends UserInfoPage {
	//
	private WebElement ordering;

	public CustomerHomePage(WebDriver driver) {
		super(driver);
		this.ordering = driver.findElement(By
				.xpath("//ul[@id='nav']/li/a[text()='Ordering']"));
	}

	// Get Elements
	public WebElement getOrdering() {
		return this.ordering;
	}

	// Set Data
	public void clickOrdering() {
		this.ordering.click();
	}

	// Business Logic
	public OrderingPage gotoOrdering() {
		clickOrdering();
		return new OrderingPage(driver);
	}

}
