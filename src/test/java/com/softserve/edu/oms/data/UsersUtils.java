package com.softserve.edu.oms.data;

import java.util.ArrayList;
import java.util.List;

public class UsersUtils {
	private static final String USERS_CSV_FILE_NAME = "/users.csv";
	private static final String USERS_EXCEL_FILE_NAME = "/users.xlsx";

	public List<IUser> getAllUsersCSV() {
		System.out.println("+++++PATH = "+ this.getClass().getResource(USERS_CSV_FILE_NAME)
				.getPath());
		return getAllUsersCSV(this.getClass().getResource(USERS_CSV_FILE_NAME)
				.getPath().substring(1));
	}

	public List<IUser> getAllUsersCSV(String absoluteFilePath) {
		List<IUser> users = new ArrayList<IUser>();
		for (List<String> row : (new CSVUtils())
				.getAllCells(absoluteFilePath)) {
			users.add(User.get()
					.setFirstname(row.get(0))
					.setLastname(row.get(1))
					.setLogin(row.get(2))
					.setPassword(row.get(3))
					.setEmail(row.get(4))
					.setRegion(row.get(5))
					.setRole(row.get(6))
					.build());
		}
		return users;
	}

	public List<IUser> getAllUsersExcel() {
		return getAllUsersExcel(this.getClass()
				.getResource(USERS_EXCEL_FILE_NAME).getPath().substring(1));
	}

	public List<IUser> getAllUsersExcel(String absoluteFilePath) {
		List<IUser> users = new ArrayList<IUser>();
		for (List<String> row : (new ExcelUtils())
				.getAllCells(absoluteFilePath)) {
			users.add(User.get()
					.setFirstname(row.get(0))
					.setLastname(row.get(1))
					.setLogin(row.get(2))
					.setPassword(row.get(3))
					.setEmail(row.get(4))
					.setRegion(row.get(5))
					.setRole(row.get(6))
					.build());
		}
		return users;
	}

}
