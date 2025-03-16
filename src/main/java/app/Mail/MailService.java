package app.Mail;

import app.web.MailRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class MailService {

    private final MailSender mailSender;
    private final MailRepository mailRepository;

    public MailService(MailSender mailSender, MailRepository mailRepository) {
        this.mailSender = mailSender;
        this.mailRepository = mailRepository;
    }

    public Mail sendEmail(MailRequest mailRequest) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailRequest.getRecipient());
        message.setSubject(mailRequest.getSubject());
        message.setText(mailRequest.getBody());

        Mail mail = Mail.builder()
                .recipient(mailRequest.getRecipient())
                .subject(mailRequest.getSubject())
                .body(mailRequest.getBody())
                .createdOn(LocalDateTime.now())
                .status(MailStatus.SUCCEEDED)
                .deleted(false)
                .build();
        try {
            mailSender.send(message);
        } catch (Exception e) {
            mail.setStatus(MailStatus.FAILED);
            log.warn("Failed to send email: {}", e.getMessage());
        }

        return mailRepository.save(mail);
    }
}

