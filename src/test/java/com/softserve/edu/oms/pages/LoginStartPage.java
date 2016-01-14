package com.softserve.edu.oms.pages;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.softserve.edu.oms.data.UrlRepository.SsuUrls;

public class LoginStartPage {
	private static volatile LoginStartPage instance = null;
	private final HashMap<Long, WebDriver> driverList;

	private LoginStartPage() {
		this.driverList = new HashMap<Long, WebDriver>();
	}

	public static LoginStartPage get() {
		if (instance == null) {
			synchronized (LoginStartPage.class) {
				if (instance == null) {
					instance = new LoginStartPage();
				}
			}
		}
		return instance;
	}

	public static LoginPage load() {
		WebDriver driver = getWebDriver();
		if (driver == null) {
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			get().driverList.put(Thread.currentThread().getId(), driver);
		}
		//
		return instance.login();
	}

	public static WebDriver getWebDriver() {
		return get().driverList.get(Thread.currentThread().getId());
	}

	public LoginPage login() {
		WebDriver webDriver = getWebDriver();
		webDriver.get(SsuUrls.LOGOUT.toString());
		webDriver.get(SsuUrls.LOGIN.toString());
		return new LoginPage(webDriver);
	}

	public LoginPage logout() {
		WebDriver webDriver = getWebDriver();
		webDriver.get(SsuUrls.LOGOUT.toString());
		return new LoginPage(webDriver);
	}

	public static void quit() {
		if (instance != null) {
			for (WebDriver webDriver : instance.driverList.values()) {
				webDriver.quit();
			}
		}
	}

}
