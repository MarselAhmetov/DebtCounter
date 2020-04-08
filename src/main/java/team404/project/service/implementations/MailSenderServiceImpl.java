package team404.project.service.implementations;

import lombok.Setter;
import org.springframework.stereotype.Service;
import team404.project.model.MailMessage;
import team404.project.service.interfaces.MailSenderService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Setter
public class MailSenderServiceImpl implements MailSenderService {

    private String username;
    private String password;
    private Properties properties;


    public void sendMail(MailMessage mailMessage) {
        new Thread(() -> {
            Session session = Session.getInstance(properties, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(username));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailMessage.getMailTo()));
                message.setSubject(mailMessage.getSubject());
                message.setContent(mailMessage.getText(), "text/html");
                //message.setText(text);

                Transport.send(message);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

        }).start();
    }

}
