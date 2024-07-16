package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;
import utils.DatabaseConnectivity;
import utils.PasswordHash;

public class UserDao {
    private Connection conn;

    public UserDao() {
        try {
			this.conn = DatabaseConnectivity.getDbConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public boolean saveUser(User user) {
        try {
            if (!isUserExists(user)) {
                return insertUserData(user);
            } else {
                return false; // User already exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean isUserExists(User user) throws SQLException {
        String query = "SELECT COUNT(*) FROM user WHERE userName=? OR userEmail=? OR userPhone=?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setLong(3, user.getPhone());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        }
        return false;
    }

    private boolean insertUserData(User user) throws SQLException {
        String query = "INSERT INTO user(userFirstName, userLastName, userName, userEmail, userAddress, userPhone, userCategory, userPassword) VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, user.getFirstname());
            statement.setString(2, user.getLastname());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getAddress());
            statement.setLong(6, user.getPhone());
            statement.setInt(7, 2);
            statement.setString(8, user.getPassword());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        }
    }

    public int getUserLoginInfo(String email, String password) {
    	System.out.println(password);
        String query = "SELECT userPassword FROM user WHERE userEmail = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) 
            {
                String hashedPasswordFromDB = resultSet.getString("userPassword");
                System.out.println(hashedPasswordFromDB);
                if (PasswordHash.verifyPassword(password, hashedPasswordFromDB)) {
                	System.out.println("passed");
                    return 1; // Passwords match
                } else 
                {
                	System.out.println("not passed");
                    return 0; // Passwords don't match
                }
            } 
            else 
            {
                return -2; // No user found with the given email
            }
        } catch (SQLException e) 
        {
        	System.out.println("not passedexception");
            e.printStackTrace();
            return -1; // Database error
        }
    }

    public List<User> getAllUser() {
        List<User> userList = new ArrayList<>();
        try {
            String query = "SELECT * FROM user";
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    User user = new User();
                    user.setFirstname(resultSet.getString("userFirstName"));
                    user.setLastname(resultSet.getString("userLastName"));
                    user.setUsername(resultSet.getString("userName"));
                    user.setEmail(resultSet.getString("userEmail"));
                    user.setAddress(resultSet.getString("userAddress"));
                    user.setPhone(resultSet.getLong("userPhone"));
                    userList.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
    
    public boolean deleteUser(String email) {
        String query = "DELETE FROM user WHERE userEmail=?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, email);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("User not deleted");
            return false;
        }
    }
    public boolean updateUser(User user, String oldEmail) {
        Connection conn = null;
        PreparedStatement pstmtUser = null;
        PreparedStatement pstmtCart = null;
        boolean success = false;

        String updateUserQuery = "UPDATE user SET userFirstName=?, userLastName=?, userName=?, userEmail=?, userPhone=?, userAddress=? WHERE userEmail=?";
        String updateCartQuery = "UPDATE cart SET userEmail=? WHERE userEmail=?";

        try {
            conn = DatabaseConnectivity.getDbConnection();
            conn.setAutoCommit(false); // Start transaction

            // Update user table
            pstmtUser = conn.prepareStatement(updateUserQuery);
            pstmtUser.setString(1, user.getFirstname());
            pstmtUser.setString(2, user.getLastname());
            pstmtUser.setString(3, user.getUsername());
            pstmtUser.setString(4, user.getEmail());
            pstmtUser.setLong(5, user.getPhone());
            pstmtUser.setString(6, user.getAddress());
            pstmtUser.setString(7, oldEmail);
            int userUpdated = pstmtUser.executeUpdate();

            // Update cart table
            pstmtCart = conn.prepareStatement(updateCartQuery);
            pstmtCart.setString(1, user.getEmail());
            pstmtCart.setString(2, oldEmail);
            int cartUpdated = pstmtCart.executeUpdate();

            // Commit transaction if both updates are successful
            if (userUpdated == 1 && cartUpdated == 1) {
                conn.commit();
                success = true;
            } else {
                conn.rollback();
            }
        } catch (SQLException ex) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            try {
                if (pstmtUser != null) {
                    pstmtUser.close();
                }
                if (pstmtCart != null) {
                    pstmtCart.close();
                }
                if (conn != null) {
                    conn.setAutoCommit(true); // Reset auto-commit to true
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public User getUserByEmail(String email) {
        User user = null;
        String query = "SELECT * FROM user WHERE userEmail = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setFirstname(resultSet.getString("userFirstName"));
                user.setLastname(resultSet.getString("userLastName"));
                user.setUsername(resultSet.getString("userName"));
                user.setEmail(resultSet.getString("userEmail"));
                user.setAddress(resultSet.getString("userAddress"));
                user.setPhone(resultSet.getLong("userPhone"));
                user.setRole(resultSet.getInt("userCategory"));
            }
        } catch (SQLException e) {
            // Handle the exception gracefully
            System.err.println("Error fetching user by email: " + email);
            e.printStackTrace();
        }
        return user;
    }
    public int getUserCount() {
        int count = 0;
        String query = "SELECT COUNT(*) AS user_count FROM user";
        try (
             PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                count = resultSet.getInt("user_count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

}
