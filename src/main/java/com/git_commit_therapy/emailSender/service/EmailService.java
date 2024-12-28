package com.git_commit_therapy.emailSender.service;

import com.git_commit_therapy.emailSender.model.Email;

public interface EmailService {
    boolean sendEmail(Email email);
}
