import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.*;
import java.sql.*;
public class StudentManagement {

    private static final String URL = "jdbc:mysql://localhost:3306/test_schem";
    private static final String USER = "root";
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    public static void main(String[] args) throws SQLException{

        int attendance_percentage;
        String full_name;
        String choice;
        int deleteChoice;


        Scanner sc = new Scanner(System.in);
        System.out.println("add or minus");
        choice = sc.nextLine();

        if (choice.equals("a")){

            System.out.println("Enter student detail");
            System.out.println("fullname:");
            full_name = sc.nextLine();
            System.out.println("Attedance percentage: ");
            attendance_percentage = sc.nextInt();

            sendData( full_name, attendance_percentage);

        } else if (choice.equals("m")){
            System.out.println("Enter the number of student you want to delete ");
            deleteChoice = sc.nextInt();

            deleteStudent(deleteChoice);


        }
    }

    public static void sendData( String full_name, int attendance_percentage) throws SQLException{
        String sql = "insert into students( full_name, attendance_percentage ) values (?,?)";

        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setString(1, full_name);
        stmt.setInt(2, attendance_percentage);

        stmt.executeUpdate();
    }

    public static void deleteStudent(int deleteChoice) throws SQLException{
        String sql = " delete from students where student_id = ? ";

        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setInt(1,deleteChoice);

        stmt.executeUpdate();



    }


}
