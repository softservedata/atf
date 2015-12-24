package com.softserve.edu.oms.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginValidatorPage extends LoginPage {
	public static final String START_VALIDATOR_MESSAGE ="Your login attempt was not successful";
	//
	private WebElement validator;

	public LoginValidatorPage(WebDriver driver) {
		super(driver);
		this.validator = driver.findElement(By.xpath("//font[@color='red']"));
	}

	// Get Elements

	public WebElement getValidator() {
		return this.validator;
	}

	public String getValidatorText() {
		return this.validator.getText();
	}

}
