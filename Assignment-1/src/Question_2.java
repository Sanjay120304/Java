// Q-2 : Write a Java Program using JDBC to establish a connection to mysql database and
// insert data using prepared statement. (Fields : Stud_Id, Stud_name, Stud_sem, Stud_Mob).
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class Question_2{
    public static Connection connect(){
        
        Connection conn = null;
        String uname = "root";
        String pass = "";
        String db_url = "jdbc:mysql://localhost:3306/school";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            conn = DriverManager.getConnection(db_url,uname,pass);

        }
        catch(Exception e){
            System.out.println("Connection Failed!" +e.getMessage());
        }
        return conn;
    }

    public static void insert(String stu_id,String stu_nm,String stu_sem,String stu_mob){
        Connection conn = connect();    
        String q = "insert into stu_rec value (?,?,?,?)";
        try {
            PreparedStatement p1 = conn.prepareStatement(q);
            p1.setString(1, stu_id);
            p1.setString(2, stu_nm);
            p1.setString(3, stu_sem);
            p1.setString(4, stu_mob);
            if(p1.executeUpdate() == 1){
                System.out.println("Inserted...");

            }

        }
        catch(SQLException e){
            System.out.println("Error occurs..."+e.getMessage());
        }
    }
    public static void main(String[] args) {
        insert("13", "Vinay", "8", "4254447474");
    }

    
}