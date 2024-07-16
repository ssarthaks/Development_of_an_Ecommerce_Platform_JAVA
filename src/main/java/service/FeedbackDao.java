package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Feedback;
import utils.DatabaseConnectivity;

public class FeedbackDao {
    // Insert feedback into the database
    public static boolean insertFeedback(Feedback feedback) throws SQLException {
        try (Connection conn = DatabaseConnectivity.getDbConnection()) {
            String sql = "INSERT INTO feedback (name, userEmail, message) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, feedback.getName());
            statement.setString(2, feedback.getEmail());
            statement.setString(3, feedback.getMessage());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
    }
    public static List<Feedback> getAllFeedback() throws SQLException {
        List<Feedback> feedbackList = new ArrayList<>();
        try (Connection conn = DatabaseConnectivity.getDbConnection()) {
            String sql = "SELECT * FROM feedback";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("userEmail");
                String message = resultSet.getString("message");
                Feedback feedback = new Feedback(name, email, message);
                feedbackList.add(feedback);
            }
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return feedbackList;
    }
}
