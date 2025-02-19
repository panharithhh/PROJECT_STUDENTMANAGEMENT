import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.sql.*;


public class login {

    public static void main(String[] args) throws SQLException,MessagingException {
        Scanner sc = new Scanner(System.in);
        System.out.println("gimme a hug: \n");
        String inputLine = sc.nextLine();
        System.out.println(inputLine);

        String[] arrayOfInformation = sqlData();

        boolean condition = false;
        for(int i =0; i<arrayOfInformation.length; i++){
            if(inputLine.equals(arrayOfInformation[i])){
                condition = true;
                break;
            } else {
                condition = false;
            }

        }

        if(condition == true){
            System.out.println("Login sucessfull");
        } else  {
            System.out.println("Login failed");
        }
    }

    public static String[] sqlData() throws SQLException, MessagingException {
        String sqlString = " select * from educators ";
        String URL = "jdbc:mysql://localhost:3306/test_schem";
        String USER = "root";
        String PASSWORD = System.getenv("DB_PASSWORD");

        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement stmt = con.prepareStatement(sqlString);

        ResultSet rs = stmt.executeQuery();

        ArrayList<String >list = new ArrayList<>();

        while(rs.next()){
            String email = rs.getString("email");
            String password = rs.getString("password_hash");

            String row = email + " " + password;
            list.add(row);
        }
        String[] educatorsArray = new String[100];
        educatorsArray = list.toArray(new String[0]);

        return educatorsArray;
    }
}
