package coursecheckprogress;
import java.util.*;
import java.sql.*;

public class Attendance {

    private static final String URL = "jdbc:mysql://localhost:3306/test_schem";
    private static final String USER = "root";
    private static final String PASSWORD = System.getenv("DB_PASSWORD");
    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the attendance gang");
        int attendace = sc.nextInt();
        System.out.println("what is the student attendance");

        int student = sc.nextInt();
        Students studentObj = returnStudent(student);
        if(studentObj == null){
            System.out.println("invalid student_id");
        } else {
            String full_name = studentObj.getFull_name();
            int student_id = studentObj.getStudent_id();
            sendAttendance(full_name, student_id, attendace);
        }
    }


    public static Students returnStudent(int student) throws SQLException{

        String sql = "select student_id, full_name from students where student_id = ? ";


        Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1,student);
        ResultSet rs = stmt.executeQuery();

        if(rs.next()){

            int student_id = rs.getInt("student_id");
            String name = rs.getString("full_name" );
            Students stud = new Students(student_id, name);
            return stud;

        }
        return null;
    }

    static void sendAttendance(String full_name ,int student_id,int attendance) throws SQLException{
        String sql = "insert into attendance (student_id, full_name, attendance) values (?,?,?)";

        Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setInt(1,student_id);
        stmt.setString(2,full_name );
        stmt.setInt(3,attendance);

        stmt.executeUpdate();
    }
}
