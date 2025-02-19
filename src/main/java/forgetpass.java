import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;
import java.sql.*;

public class forgetpass {
    public static void main(String[] args) throws  SQLException, MessagingException{

        Scanner sc = new Scanner(System.in);
        System.out.println(" Enter your email");
        String email = sc.nextLine();

        sendPassword(email);

    }

    public static void sendPassword(String email) throws SQLException, MessagingException {

        String sql = " select email,password_hash from educators";
        String URL = "jdbc:mysql://localhost:3306/test_schem";
        String USER = "root";
        String PASSWORD = System.getenv("DB_PASSWORD");
        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement stmt = con.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();

        while(rs.next()){

            String emailDB = rs.getString("email");
            String password = rs.getString("password_hash");

            if(email.equals(emailDB)){

                String senderEmail = "burberrith609@gmail.com";
                String senderPassword = System.getenv("EMAIL_APP_PASSWORD");

                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");

                Session session = Session.getInstance(props, new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(senderEmail, senderPassword);
                    }
                }
                );

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(senderEmail));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                message.setSubject("Your password");
                message.setText("Your password is: " + password);
                Transport.send(message);
                System.out.println("Email sent successfully.");

            }
            }
        }
    }


