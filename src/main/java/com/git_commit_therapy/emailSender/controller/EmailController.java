package com.git_commit_therapy.emailSender.controller;

import com.git_commit_therapy.emailSender.model.Email;
import com.git_commit_therapy.emailSender.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/email")
    public Boolean sendEmail(@RequestBody Email email) {
        return emailService.sendEmail(email);
    }
}
