import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        // SMTP Configuration
        String senderPassword = System.getenv("EMAIL_APP_PASSWORD");
        String senderEmail = "burberrith609@gmail.com";
        String receiverEmail = "";

        System.out.println("Email you want to send to");
        receiverEmail = sc.nextLine();
        String authenticationCode = authenticationGenerator.generateCode();


        boolean emailSent = sendEmail(senderEmail, senderPassword, receiverEmail, authenticationCode);// Just to send da code to our email
        if(!emailSent){
            System.out.println("Email not sent!");
        } else {
            System.out.println("Enter the user Authentication code");
            String userAnswer = sc.nextLine();

            if(userAnswer.equals(authenticationCode)){
                System.out.println("Congrats");

            }
            else {
                System.out.println("Wrong code my freind");
            }
        }

    }

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
