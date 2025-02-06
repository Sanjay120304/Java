import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadRecords {
    public static void readRecords() {
        try (Connection conn = DbConfig.getConnection();
             Statement stmt = conn.createStatement()) {

            String selectQuery = "SELECT * FROM mytable";
            ResultSet rs = stmt.executeQuery(selectQuery);
            System.out.println("\nID | Name | Email");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                System.out.printf("%d | %s | %s%n", id, name, email);
            }
        } catch (SQLException e) {
            System.out.println("Error reading records: " + e.getMessage());
        }
    }
}