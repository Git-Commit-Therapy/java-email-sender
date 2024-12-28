package com.git_commit_therapy.emailSender.service.impl;

import com.git_commit_therapy.emailSender.model.Email;
import com.git_commit_therapy.emailSender.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Log
@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public boolean sendEmail(Email email) {
        log.info("Sending email from: " + email.getSender());
        boolean status = true;
        //If email contains an attachment
        if(email.getAttachment() != null) {
            try{
                MimeMessage mimeMessage = mailSender.createMimeMessage();
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
                mimeMessageHelper.setFrom(email.getSender());
                mimeMessageHelper.setTo(email.getRecipient());
                mimeMessageHelper.setSubject(email.getSubject());
                mimeMessageHelper.setText(email.getBody(), true);

                mimeMessageHelper.addInline(email.getAttachment().getName(), email.getAttachment());

                mailSender.send(mimeMessage);
            }catch (MessagingException e){
                log.warning("Error while sending the email. Exception: "+e.getMessage());
                status = false;
            }
        }else{
            try{
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(email.getSender());
                message.setTo(email.getRecipient());
                message.setSubject(email.getSubject());
                message.setText(email.getBody());
                mailSender.send(message);
            }catch (MailException e){
                log.warning("Error while sending the email. Exception: "+e.getMessage());
                status = false;
            }

        }
        return status;
    }
}
