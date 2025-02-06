//netstat -an | findstr 33060
// represents a connection to a database
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class createdb {
  public static void main(String[] args) {
    // Define the database connection parameters
    String dbUrl = "jdbc:mysql://localhost:3306/";
    String username = "root";
    String password = "";
    String databaseName = "cr";

    // loads the MySQL JDBC driver using the Class.forName() method
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println("Error loading MySQL JDBC driver: " + e.getMessage());
      return;
    }

    // Create a connection to the database
    /*Some of the key methods of the Connection interface include:

createStatement(): creates a Statement object for executing SQL statements.
prepareStatement(): creates a PreparedStatement object for executing parameterized SQL statements.
executeQuery(): executes a SQL query and returns a ResultSet object.
commit(): commits the current transaction.
rollback(): rolls back the current transaction.
close(): closes the connection to the database. */
    //Connection is an interface in Java, and it's part of the java.sql package.
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(dbUrl, username, password);
    } catch (SQLException e) {
      System.out.println("Error connecting to database: " + e.getMessage());
      return;
    }

    // Create a statement object
    Statement stmt = null;
    try {
      stmt = conn.createStatement();
    } catch (SQLException e) {
      System.out.println("Error creating statement: " + e.getMessage());
      return;
    }

    // Create the database
    try {
      stmt.executeUpdate("CREATE DATABASE " + databaseName);
      System.out.println("Database created successfully!");
    } catch (SQLException e) {
      System.out.println("Error creating database: " + e.getMessage());
    }

    // Close the statement and connection
    try {
      stmt.close();
      conn.close();
    } catch (SQLException e) {
      System.out.println("Error closing statement or connection: " + e.getMessage());
    }
  }
}