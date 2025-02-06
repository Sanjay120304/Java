import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConfig {
    
    public static final String DB_URL = "jdbc:mysql://localhost:3306/";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "";
    public static final String DB_NAME = "crud";

    public static Connection getConnection() throws SQLException {
        // Step 1: Try to connect to the MySQL server without specifying a database
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();

        // Step 2: Check if the database exists, if not create it
        String checkDatabaseQuery = "SHOW DATABASES LIKE '" + DB_NAME + "'";
        var resultSet = statement.executeQuery(checkDatabaseQuery);

        if (!resultSet.next()) {
            // Database does not exist, create it
            String createDatabaseQuery = "CREATE DATABASE " + DB_NAME;
            statement.executeUpdate(createDatabaseQuery);
            System.out.println("Database not found. Creating the database...");
        } else {
            System.out.println("Database already exists.");
        }

        // Step 3: Now, connect to the database
        connection = DriverManager.getConnection(DB_URL + DB_NAME, DB_USERNAME, DB_PASSWORD);
        System.out.println("Connected to the database successfully!");
        
        return connection;
    }

    public static void main(String[] args) {
        try {
            // Attempt to get the connection to the database
            Connection connection = getConnection();

            // Optional: Perform any database operations (e.g., create a table, etc.)

            // Close the connection
            connection.close();
        } catch (SQLException e) {
            System.err.println("Error while connecting to the database: " + e.getMessage());
        }
    }
}
