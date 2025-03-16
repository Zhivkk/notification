package app.web;

import app.Mail.Mail;
import app.Mail.MailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/email")
public class MailController {

    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping
    public ResponseEntity <MailResponse> sendEmail(@RequestBody MailRequest mailRequest) {

        Mail mail = mailService.sendEmail(mailRequest);
        MailResponse response = DTOMapper.fromMail(mail);

        return ResponseEntity.status((HttpStatus.CREATED)).body(response);
    }

}

