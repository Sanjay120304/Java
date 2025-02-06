import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class CreateRecord {
    public static void createRecord(Scanner scanner) {  
        try (Connection conn = DbConfig.getConnection()) {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter email: ");
            String email = scanner.nextLine();

            String insertQuery = "INSERT INTO mytable (name, email) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                pstmt.setString(1, name);
                pstmt.setString(2, email);
                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Record created successfully!");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error creating record: " + e.getMessage());
        }
    }
}
