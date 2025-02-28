// Q-3 : Write a Java Program using JDBC to edit (Update/Delete) student’s profile stored in
// database.



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;


public class Question_3{
   public static Connection connect(){
    Connection conn = null;
    String uname = "root";
    String pass = "";
    String dburl = "jdbc:mysql://localhost:3306/school";

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(dburl,uname,pass);

    } catch (Exception e) {

        System.out.println("Error occurs..."+e.getMessage());
    }
    return conn;

   }
   public static void update(String id ,String name,String sem ,String mob){
    Connection conn = connect();
    String q = "update stu_rec set nm = ?,sem = ?,mob = ? where id = ?"; 

    try {
        PreparedStatement p1 = conn.prepareStatement(q);

        p1.setString(1, name);
        p1.setString(2, sem);
        p1.setString(3, mob);
        p1.setString(4, id);
        
        int row = p1.executeUpdate();

        if(row > 0){
            System.out.println("Updated Record Succesfully....");
        }
        else {
            System.out.println("No record found with id " +id);
        }
        // if(p1.executeUpdate() == 1){
        //     System.out.println("Updated Record Succesfully....");
        // }
    } catch (SQLException e) {

    System.out.println("Error occurs..."+e.getMessage());
    
    }finally{
        try {
            conn.close();
        } catch (Exception e) {
            System.out.println("Error occurs while closing connection.."+e.getMessage());

        }
 
    }
}

    public static void delete(String id){

    Connection conn = connect();

    String q1 = "delete from stu_rec where id = ?";

    try{

        PreparedStatement p2 = conn.prepareStatement(q1);
        p2.setString(1, id);

        int rows = p2.executeUpdate();

        if (rows > 0){
            System.out.println("Deleted Record Succesfully....");
        }
        else{
            System.out.println("No record found with id"+id);
        }
    }
    catch(SQLException e){
        System.out.println("Error occurs...."+e.getMessage());

    }finally{
        try {
            conn.close();
        } catch (Exception e) {
            System.out.println("Error occurs while closing connection.."+e.getMessage());

        }
 
    }

   }
   public static void main(String[] args) {
       Scanner s = new Scanner(System.in);
       int ch;
       do { 
           System.out.println("\n ==========School Database Menu==========");
           System.out.println("1.Update Record");
           System.out.println("2.Delete Record");
           System.out.println("3.Exit");
           ch = s.nextInt();
           s.nextLine();
           switch (ch) {
               case 1:
                System.out.println("Enter Student Id:");
                String id = s.nextLine();
                System.out.println("Enter Student Name:");
                String name = s.nextLine();
                System.out.println("Enter Student Semester:");
                String sem = s.nextLine();
                System.out.println("Enter Student Mobile Number:");
                String mob = s.nextLine();
                update(id, name, sem, mob);
                break;
               case 2:
                  System.out.println("Enter Student Id :");
                  String del_id = s.nextLine(); 
                  delete(del_id);
                  break;
               case 3:
                   System.out.println("Exiting Program...");
                   break;
               default:
                   System.out.println("⚠️ Invalid Choice! Please enter 1, 2, or 3");
           }
       } while (ch!= 3);
   }


}
