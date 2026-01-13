package com.example.job.email.sender.controller;

import com.example.job.email.sender.service.EmailService;
import com.example.job.email.sender.util.ExcelReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/api/send")
public class EmailController {

    @Value("${resume.file.path}")
    private String resumePath;

    @Value("${excel.file.path}")
    private String excelPath;

    private final EmailService emailService;
    private final ExcelReader excelReader;

    public EmailController(EmailService emailService,
                           ExcelReader excelReader) {
        this.emailService = emailService;
        this.excelReader = excelReader;
    }

    @PostMapping("/resume")
    public String sendResumeToRecruiters() {

        File resume = new File(resumePath);
        List<String> emails = excelReader.readEmails(excelPath);

        int successCount = 0;

        for (String email : emails) {
            try {
                emailService.sendEmailWithResume(email, resume);
                successCount++;

                // Avoid spam detection
                Thread.sleep(2000);

            } catch (Exception e) {
                System.out.println("Failed for: " + email);
            }
        }

        return "Emails sent successfully to " + successCount + " recruiters.";
    }
}

