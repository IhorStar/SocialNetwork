package mail;

import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmailWithRegistrationData {

    private static final Logger LOGGER = LogManager.getLogger(SendEmailWithRegistrationData.class);

    Properties properties;
    Session session;
    MimeMessage message;
    private User user = new User();
    private String internetAddress = user.getEmail();
    private String username = user.getName();
    private String password = user.getPassword();

    public void generateAndSendMessage() {
        properties = System.getProperties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        session = Session.getDefaultInstance(properties);

        message = new MimeMessage(session);
        try {
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(internetAddress));
            message.setSubject("Registration data");
            String emailBody = "Your username: " + username + ", your password: " + password;
            message.setText(emailBody);

            Transport.send(message);
        } catch (MessagingException e) {
            LOGGER.error("MessagingExсeption", e);
        }
    }
}
