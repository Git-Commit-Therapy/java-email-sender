package com.git_commit_therapy.emailSender.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.File;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Email {
    private String sender;
    private String recipient;
    private String subject;
    private String body;
    private File attachment;
}
