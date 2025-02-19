import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class TestClassTest {

    private String validSenderEmail;
    private String validSenderPassword;
    private String validReceiverEmail;
    private String testAuthCode;

    @BeforeEach
    void setUp() {
        validSenderEmail = "burberrith609@gmail.com";
        validSenderPassword = System.getenv("EMAIL_APP_PASSWORD");
        validReceiverEmail = "testuser@example.com";
    }

    @Test
    void testSendEmail() {
        boolean result = test.sendEmail(
                validSenderEmail,
                validSenderPassword,
                validReceiverEmail,
                testAuthCode
        );
        Assertions.assertTrue(result, "Email should be sent successfully.");
    }

    @Test
    void testCreateEducator() {
        String name = "Test User";
        String email = "testuser@example.com";
        String password = "test1234";

        try {
            test.createEducator(name, email, password);
            Assertions.assertTrue(true, "Educator created without error.");
        } catch (SQLException e) {
            Assertions.fail("SQLException occurred: " + e.getMessage());
        }
    }
}