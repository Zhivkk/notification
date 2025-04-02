package app;

import app.Mail.Mail;
import app.Mail.MailRepository;
import app.Mail.MailService;
import app.web.MailRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MailServiceUTest {

    @Mock
    private MailSender mailSender;

    @Mock
    private MailRepository mailRepository;

    @InjectMocks
    private MailService mailService;

    @Test
    void testSendEmailSuccess() {

        MailRequest mailRequest = MailRequest.builder()
                .recipient("recipient@example.com")
                .subject("Test Subject")
                .body("Test Body")
                .build();


        Mail mail = mailService.sendEmail(mailRequest);


        verify(mailSender).send(any(SimpleMailMessage.class));
        verify(mailRepository).save(any(Mail.class));
    }

}
