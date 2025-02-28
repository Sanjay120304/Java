// Q-1 : Write a Java Program using JDBC to display students record (enrollment_num, Name,
// Address, Mob_no, and E_mail ) from table student_rec.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Question_1 {

    public static Connection connected(){
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/school";
        String user = "root";
        String pass = "";

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url,user,pass);
            
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection Failed!" +e.getMessage());
        
        }
        return conn;
    }
    public static void display(){
        Connection conn = connected();
        if(conn == null){
            System.out.println("Failed to connect.");
            return;
        }
        // String q ="Select * from student_rec";
        String q = "SELECT enrollment, Name, Adde, mob, mail FROM student_rec";
        try (Statement stmt = conn.createStatement(); ResultSet rs =stmt.executeQuery(q)){
            System.out.println("--------------------------------------------------------------------------------------------");
            System.out.printf("%-15s %-20s %-30s %-15s %-30s%n","Enrollment","Name","adde","Mobile No","Email");
            System.out.println("---------------------------------------------------------------------------------------------");
             while(rs.next()){

                System.out.printf("%-15s %-20s %-30s %-15s %-30s%n",
                rs.getString("enrollment"),
                rs.getString("Name"),
                rs.getString("adde"),
                rs.getString("mob"),
                rs.getString("mail"));
             }
             System.out.println("--------------------------------------------------------------------------------------------");

        } catch (Exception e) {
            System.out.println("Error Fetching Records:"+e.getMessage());
        } finally{
            try {
                conn.close();
            } catch (SQLException e) {
            System.out.println("Error closing connection:"+e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        display();
    }
    
}






































//using select all.........................
// String q = "SELECT * FROM student_rec"; // Select all columns

// try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(q)) {
//     ResultSetMetaData rsmd = rs.getMetaData();
//     int columnCount = rsmd.getColumnCount();

//     // Print column headers
//     System.out.println("-----------------------------------------------------------");
//     for (int i = 1; i <= columnCount; i++) {
//         System.out.printf("%-20s", rsmd.getColumnName(i)); // Adjust column width as needed
//     }
//     System.out.println("\n-----------------------------------------------------------");

//     // Print table data
//     while (rs.next()) {
//         for (int i = 1; i <= columnCount; i++) {
//             System.out.printf("%-20s", rs.getString(i)); // Fetch all columns dynamically
//         }
//         System.out.println();
//     }
//     System.out.println("-----------------------------------------------------------");
