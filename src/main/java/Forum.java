import java.util.*;
import java.sql.*;

// course_title varchar(123) not null,
// description Text,

public class Forum {

    private static final String URL = "jdbc:mysql://localhost:3306/test_schem";
    private static final String USER = "root";
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    public static void main(String[] args) throws SQLException {

        String choice;
        String text;
        String title;
        int  deleteChoice;


        Scanner sc = new Scanner(System.in);
        System.out.println("add or minus");
        choice = sc.nextLine();

        if (choice.equals("a")){

            System.out.println("Enter your title:");
            title = sc.nextLine();
            System.out.println("Enter the description ");
            text = sc.nextLine();

            CreateForum( title,text);

        } else if (choice.equals("m")){
            System.out.println("Enter the course you want to delete ");
            deleteChoice = sc.nextInt();

            deleteForum(deleteChoice);

        }


    }

    public static void CreateForum(String title, String text) throws SQLException{
        String sql = "insert into forums( topic, description ) values (?,?)";

        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setString(1, title);
        stmt.setString(2, text);

        stmt.executeUpdate();
    }

    public static void deleteForum(int deleteChoice) throws SQLException{
        String sql = " delete from forums where student_id = ? ";

        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement stmt = con.prepareStatement(sql);

        stmt.setInt(1,deleteChoice);

        stmt.executeUpdate();
    }
}
