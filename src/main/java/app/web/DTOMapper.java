package app.web;

import app.Mail.Mail;

public class DTOMapper {

    public static MailResponse fromMail(Mail mail) {
        return MailResponse.builder()
                .subject(mail.getSubject())
                .status(mail.getStatus())
                .createdOn(mail.getCreatedOn())
                .build();
    }
}
