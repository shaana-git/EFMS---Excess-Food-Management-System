package src.src;

import java.sql.*;

public class SessionManager {
    private static SessionManager instance;
    private int userId;
    private String userType;
    private Connection connection;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/food_management";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    private SessionManager() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void setUserData(int userId, String userType) {
        this.userId = userId;
        this.userType = userType;

        String insertSessionSQL = "INSERT INTO session (user_id, user_type, is_active) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(insertSessionSQL)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, userType);
            pstmt.setBoolean(3, true);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getUserId() {
        return userId;
    }
    public String getUserType(){
        return userType;
    }

    public void clearSession() {
        String updateSessionSQL = "UPDATE session SET is_active = false WHERE user_id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(updateSessionSQL)) {
            pstmt.setInt(1, this.userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

