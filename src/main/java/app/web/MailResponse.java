package app.web;

import app.Mail.MailStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data@Builder
public class MailResponse {

    private String subject;
    private LocalDateTime createdOn;
    private MailStatus status;


}
