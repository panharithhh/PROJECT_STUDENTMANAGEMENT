package signupandlogin;

import java.util.*;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

public class login {

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your email:");
        String typedEmail = sc.nextLine();
        System.out.println("Enter your password:");
        String typedPassword = sc.nextLine();

            boolean success = verifyCredentials(typedEmail, typedPassword);

            if (success) {
                System.out.println("Login successful");
            } else {
                System.out.println("Login failed");
            }
    }

    private static boolean verifyCredentials(String email, String plainPassword) throws SQLException {
        String URL = "jdbc:mysql://localhost:3306/test_schem";
        String USER = "root";
        String PASSWORD = System.getenv("DB_PASSWORD");
        String sql = "SELECT password_hash FROM educators WHERE email = ?";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) { // extract the sql string and store in rs
                if (rs.next()) { // take the data only from the first row
                    String storedHash = rs.getString("password_hash");
                    return BCrypt.checkpw(plainPassword, storedHash);// compare plain and the sql pass

                } else {
                    return false;
                }
            }
        }
    }
}