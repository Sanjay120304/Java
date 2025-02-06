import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class table {
    public static void main(String[] args) {
        // Database credentials
      String dburl = "jdbc:mysql://localhost:3306/crud";
    String username = "root";
    String password = "";
    // String databaseName = "mydb";

        // Load the MySQL JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading MySQL JDBC driver: " + e.getMessage());
            return;
        }

        // Establish a connection to the database
        try (Connection conn = DriverManager.getConnection(dburl, username, password)) {
            // Create a statement object
            try (Statement stmt = conn.createStatement()) {
                // Create the table
                String createTableQuery = "CREATE TABLE my ("
                        + "id INT AUTO_INCREMENT,"
                        + "name VARCHAR(255),"
                        + "email VARCHAR(255),"
                        + "PRIMARY KEY (id)"
                        + ")";
                stmt.executeUpdate(createTableQuery);
                System.out.println("Table created successfully!");
            } catch (SQLException e) {
                System.out.println("Error creating table: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }
}