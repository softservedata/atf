package com.softserve.edu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.softserve.edu.entity.UserDB;
import com.softserve.edu.entity.UserDB.UserDBQueries;

public class UserDao {
    private final static String DATABASE_READING_ERROR = "Database Reading Error";
    private static volatile UserDao instance = null;
    private DataSource dataSource;

    private UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static UserDao get(DataSource dataSource) {
        if (instance == null) {
            synchronized (UserDao.class) {
                if (instance == null) {
                    instance = new UserDao(dataSource);
                }
            }
        }
        return instance;
    }

    // TODO Develop get() {}

    public boolean insertUser(String login, String password, String firstname, String lastname, String email,
    		Long region, Long role) {
        Statement statement = null;
        boolean result = false;
        String query = String.format(UserDBQueries.INSERT_USER_BY_LOGIN.toString(), 
        		login, password, firstname, lastname, email, region.toString(), role.toString());
        try {
            statement = ConnectionUtils.get(dataSource).getConnection().createStatement();
            result = statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(DATABASE_READING_ERROR, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception ex) {
                    // TODO Warning
                }
            }
        }
        // TODO result must be return if delete Ok
        return result;
    }
    
    public UserDB getUserByLogin(String login) {
        UserDB user = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String query = String.format(UserDBQueries.GET_USER_BY_LOGIN.toString(), login);
        try {
            statement = ConnectionUtils.get(dataSource).getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                // TODO Use Builder
                user = new UserDB(Long.parseLong(resultSet.getString(1)), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                        Long.parseLong(resultSet.getString(7)), Long.parseLong(resultSet.getString(8)));
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(DATABASE_READING_ERROR, e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception ex) {
                    // TODO Warning
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception ex) {
                    // TODO Warning
                }
            }
        }
        return user;
    }

    public List<UserDB> getAllUsers() {
        List<UserDB> allUsers = new ArrayList<UserDB>();
        Statement statement = null;
        ResultSet resultSet = null;
        String query = UserDBQueries.GET_ALL_USERS.toString();
        try {
            statement = ConnectionUtils.get(dataSource).getConnection().createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                // TODO Use Builder
//                allUsers.add(new UserDB(Long.parseLong(resultSet.getString(1)), resultSet.getString(2),
//                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
//                        Long.parseLong(resultSet.getString(7)), Long.parseLong(resultSet.getString(8))));
            	UserDB userDB = new UserDB(Long.parseLong(resultSet.getString(1)), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), 1L, 1L);
            	if (resultSet.getString(7) != null) {
            		userDB.setRegion(Long.parseLong(resultSet.getString(7)));
            	}
            	if (resultSet.getString(8) != null) {
            		userDB.setRole(Long.parseLong(resultSet.getString(8)));
            	}
            	if ((resultSet.getString(9) == null)
            			|| (resultSet.getString(9).toLowerCase().equals("null"))) {
            		userDB.setIsUserActive(0L);
            	} else {
            		userDB.setIsUserActive(Long.parseLong(resultSet.getString(9)));
            	}
                allUsers.add(userDB);
            }
        } catch (SQLException e) {
            throw new RuntimeException(DATABASE_READING_ERROR, e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception ex) {
                    // TODO Warning
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception ex) {
                    // TODO Warning
                }
            }
        }
        System.out.println("+++++ ***** ALL USERS SIZE = " + allUsers.size());
        return allUsers;
    }

    public boolean deleteUserById(Long id) {
        Statement statement = null;
        boolean result = false;
        String query = String.format(UserDBQueries.DELETE_USER_BY_ID.toString(), id);
        try {
            statement = ConnectionUtils.get(dataSource).getConnection().createStatement();
            result = statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(DATABASE_READING_ERROR, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception ex) {
                    // TODO Warning
                }
            }
        }
        // TODO result must be return if delete Ok
        return result;
    }

	public boolean deleteUserDB(UserDB userDB) {
		return deleteUserById(userDB.getId());
	}
    
	public boolean deleteUsersByPartialLogin(String partialLogin) {
        Statement statement = null;
        boolean result = false;
        String query = String.format(UserDBQueries.DELETE_USER_BY_PARTIAL_LOGIN.toString(), partialLogin);
        try {
            statement = ConnectionUtils.get(dataSource).getConnection().createStatement();
            result = statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(DATABASE_READING_ERROR, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception ex) {
                    // TODO Warning
                }
            }
        }
        // TODO result must be return if delete Ok
        return result;
    }

}
