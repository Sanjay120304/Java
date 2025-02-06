import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteRecord {
    public static void deleteRecord(Scanner scanner) {  
        try (Connection conn = DbConfig.getConnection()) {
            System.out.print("Enter the ID of the record to delete: ");
            int id = scanner.nextInt();
            scanner.nextLine(); 

            String deleteQuery = "DELETE FROM mytable WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {
                pstmt.setInt(1, id);
                int rowsDeleted = pstmt.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Record deleted successfully!");
                } else {
                    System.out.println("No record found with the specified ID.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error deleting record: " + e.getMessage());
        }
    }
}
