package signupandlogin;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Properties;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUp {
    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);
        String senderPassword = System.getenv("EMAIL_APP_PASSWORD");
        String senderEmail = "burberrith609@gmail.com";
        String receiverEmail;
        String password_hash;
        String full_name;

        System.out.println("Email");
        receiverEmail = sc.nextLine();
        String authenticationCode = authenticationGenerator.generateCode();
        System.out.println("Name: ");
        full_name = sc.nextLine();
        System.out.println("password");
        password_hash = sc.nextLine();

        boolean emailSent = sendEmail(senderEmail, senderPassword, receiverEmail, authenticationCode);// Just to send da code to our email
        if(!emailSent){
            System.out.println("Email not sent!");
        } else {
            System.out.println("Enter the user Authentication code");
            String userAnswer = sc.nextLine();
            if(userAnswer.equals(authenticationCode)){
                System.out.println("Congrats");
                createEducator(full_name, receiverEmail, password_hash);
            }
            else {
                System.out.println("Wrong code my freind");
            }
        }

    }

     static void createEducator(String full_name, String receiverEmail, String password_hash) throws SQLException{
        String sql = " insert into educators (full_name, email, password_hash) values(?,?,?)";

         String URL = "jdbc:mysql://localhost:3306/test_schem";
         String USER = "root";
         String PASSWORD = System.getenv("DB_PASSWORD");
         Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
         PreparedStatement stmt = con.prepareStatement(sql);

         stmt.setString(1, full_name);
         stmt.setString(2, receiverEmail);
         stmt.setString(3,password_hash);
         stmt.executeUpdate();

     };


    private static boolean sendEmail(String senderEmail, String senderPassword, String receiverEmail, String authenticationCode) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail));
            message.setSubject("Your Authentication Code");
            message.setText("Your authentication code is: " +authenticationCode);

            Transport.send(message);
            System.out.println("Email sent successfully.");
            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
