package com.softserve.edu.oms.data;

import java.util.List;

import com.softserve.edu.service.UserService;

public final class UserRepository {

	private static volatile UserRepository instance = null;

	private UserRepository() {
	}

	public static UserRepository get() {
		if (instance == null) {
			synchronized (UserRepository.class) {
				if (instance == null) {
					instance = new UserRepository();
				}
			}
		}
		return instance;
	}

	public IUser getAdminUser() {
    	//return new User("ivanka","horoshko","iva","qwerty","mail@gmail.com","East","Administrator");
		// User user = new User();
		// user.setFirstname("ivanka");
		// user.setLastname("horoshko");
		// user.setLogin("iva");
		// user.setPassword("qwerty");
		// user.setEmail("mail@gmail.com");
		// user.setRegion("East");
		// user.setRole("Administrator");
		// return user;
		// return new User();
		// .setFirstname("ivanka")
		// .setLastname("horoshko")
		// .setLogin("iva")
		// .setPassword("qwerty")
		// .setEmail("mail@gmail.com")
		// .setRegion("East")
		// .setRole("Administrator")
		// .build();
    	return User.get()
    			.setFirstname("ivanka")
    			.setLastname("horoshko")
    			.setLogin("iva")
    			.setPassword("qwerty")
    			.setEmail("mail@gmail.com")
    			.setRegion("East")
    			.setRole("Administrator")
    			.build();
    }

	public IUser getCustomerUsers() {
    	return User.get()
    			.setFirstname("logincustomer")
    			.setLastname("logincustomer")
    			.setLogin("logincustomer")
    			.setPassword("qwerty")
    			.setEmail("abcd@gmail.com")
    			.setRegion("East")
    			.setRole("Customer")
    			.build();
	}

	public IUser getInvalidUser() {
		// return new User("ivanka", "horoshko", "iva1", "qwerty1",
		// "mail@gmail.com", "East", "Administrator");
		return User.get()
    			.setFirstname("ivanka")
    			.setLastname("horoshko")
    			.setLogin("iva1")
    			.setPassword("qwerty1")
    			.setEmail("mail@gmail.com")
    			.setRegion("East")
    			.setRole("Administrator")
    			.build();
	}

	public IUser getNewUser() {
		return User.get()
    			.setFirstname("inew1")
    			.setLastname("inew2")
    			.setLogin("inew3")
    			.setPassword("qwerty")
    			.setEmail("mail@gmail.com")
    			.setRegion("West")
    			.setRole("Administrator")
    			.build();
	}

	public static List<IUser> getAllUsersCSV() {
		return new UsersUtils().getAllUsersCSV();
	}
	
	public static List<IUser> getAllUsersExcel() {
		return new UsersUtils().getAllUsersExcel();
	}

	public static List<IUser> getAllUsersDB() {
		return UserService.get(DataSourceRepository.getJtdsMsSqlRemote()).getAllUsers();
	}

}
