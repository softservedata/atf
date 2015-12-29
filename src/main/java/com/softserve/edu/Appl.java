package com.softserve.edu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softserve.training.Calc;
import com.softserve.training.Some;

public class Appl {
	public static final Logger logger = LoggerFactory.getLogger(Appl.class); // LoggerFactory

	public static void main(String[] args) {
		System.out.println("Hello from App:");
		Appl appl = new Appl();
		Calc calc = new Calc();
		Some some = new Some();

		appl.appMethod();
		calc.calcMethod();
		some.someMethod();
	}

	public void appMethod() {
		logger.error("Appl Error");
		logger.warn("Appl Warning");
		logger.info("Appl Info");
		logger.debug("Appl Debug");
	}
}
