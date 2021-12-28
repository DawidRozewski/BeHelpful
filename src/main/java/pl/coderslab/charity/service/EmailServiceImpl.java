package pl.coderslab.charity.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.AppUser;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.repository.AppUserRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.security.Principal;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    @Async
    public void sendVerificationEmail(AppUser appUser) {
        String URL = "http://localhost:8080";
        String toAddress = appUser.getEmail();
        String subject = "[Be Helpful] Please verify your registration";
        String content = "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"URL\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Be Helpful, Charity Company";
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
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

    @Override
    @Async
    public void sendDonationEmail(Donation donation, Principal principal) {
        String toAddress = principal.getName();
        String subject = "[Be Helpful] Pickup details";
        String content = "<h3> Below you will find information on the details of receiving the package: </h3> <br>"
                + "<strong> Pick up date: </strong> DATE <br>"
                + "<strong> Pick up time: </strong> TIME <br>"
                + "<strong> Quantity baqs: </strong> QUANTITY <br>"
                + "<strong> Street: </strong> STREET <br>"
                + "<strong> Zip Code: </strong> ZIP_CODE <br>"
                + "<strong> Phone number: </strong>  PHONE_NUMBER <br><br>"
                + "Thank you for the gifts,<br>"
                + "Be Helpful, Charity Company";
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
            helper.setTo(toAddress);
            helper.setSubject(subject);

            content = content.replace("DATE", donation.getPickUpDate().toString());
            content = content.replace("TIME", donation.getPickUpTime().toString());
            content = content.replace("QUANTITY", donation.getQuantity().toString());
            content = content.replace("STREET", donation.getStreet());
            content = content.replace("ZIP_CODE", donation.getZipCode());
            content = content.replace("PHONE_NUMBER", donation.getPhoneNumber());

            helper.setText(content, true);
            mailSender.send(message);

        } catch (MessagingException e) {
            throw new IllegalStateException("failed to send email");
        }

    }
}
