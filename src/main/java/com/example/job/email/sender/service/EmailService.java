package com.example.job.email.sender.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender mailSender,
                        TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    public void sendEmailWithResume(
            String to,
            File resumeFile
    ) throws MessagingException {

        Context context = new Context();
        context.setVariable("name", "Mehvish Fansopkar");
        context.setVariable("phone", "+91-9876543210");
        context.setVariable("email", "mehvishfansopkar@gmail.com");

        String htmlContent = templateEngine.process("resume-email", context);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject("Java Backend Developer – Resume Submission");
        helper.setText(htmlContent, true);
        helper.setFrom("your_email@gmail.com");

        helper.addAttachment(resumeFile.getName(), resumeFile);

        mailSender.send(message);
    }
}
