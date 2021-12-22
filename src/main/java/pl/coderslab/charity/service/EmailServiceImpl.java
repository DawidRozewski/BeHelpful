package pl.coderslab.charity.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.AppUser;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendVerificationEmail(AppUser appUser) {
        String URL = "http://localhost:8080";
        String toAddress = appUser.getEmail();
        String fromAddress = "jghdsaf@gmail.com";
        String subject = "[Be Helpful] Please verify your registration";
        String content = "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"URL\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Be Helpful, Charity Company";
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
            helper.setFrom(fromAddress);
            helper.setTo(toAddress);
            helper.setSubject(subject);

            String verifyURL = URL + "/verify?code=" + appUser.getVerificationToken();
            content = content.replace("URL", verifyURL);

            helper.setText(content, true);
            mailSender.send(message);

        } catch (MessagingException e) {
            throw new IllegalStateException("failed to send email");
        }
    }
}
