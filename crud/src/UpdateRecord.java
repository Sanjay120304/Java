import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateRecord {
    public static void updateRecord(Scanner scanner) {  
        try (Connection conn = DbConfig.getConnection()) {
            System.out.print("Enter the ID of the record to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();  

            System.out.print("Enter new name: ");
            String newName = scanner.nextLine();
            System.out.print("Enter new email: ");
            String newEmail = scanner.nextLine();

            String updateQuery = "UPDATE mytable SET name = ?, email = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
                pstmt.setString(1, newName);
                pstmt.setString(2, newEmail);
                pstmt.setInt(3, id);
                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Record updated successfully!");
                } else {
                    System.out.println("No record found with the specified ID.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error updating record: " + e.getMessage());
        }
    }
}
