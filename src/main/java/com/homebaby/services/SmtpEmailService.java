package com.homebaby.services;


import com.homebaby.enums.EmailTemplate;
import com.homebaby.errors.exceptions.EmailNotSentException;
import com.homebaby.requests.EmailMessage;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class SmtpEmailService {

    private final JavaMailSender emailSender;

    private final TemplateEngine templateEngine;
    @Value("${spring.mail.enabled}")
    private boolean enabled;
    @Value("${spring.mail.from}")
    private String from;

    public void sendEmail( EmailMessage emailMessage) {
        log.info("the function send email is being executed in the thread: {}", Thread.currentThread().getName());

        if (!enabled){
            log.info("The email is not enabled!");
            return;
        }

        log.info("Sending email...");
        log.info("to: " + emailMessage.receivers());
        log.info("subject: " + emailMessage.subject());
        log.info("content: " + emailMessage.content());

        try {
            var message = emailSender.createMimeMessage();
            var messageHelper = new MimeMessageHelper(message, "UTF-8");

            messageHelper.setFrom(this.from);


            messageHelper.setTo(emailMessage.receivers().toArray(String[]::new));
            messageHelper.setSubject(emailMessage.subject());
            messageHelper.setText(emailMessage.content(), true);

            emailSender.send(message);
            log.info("Email sent");
        } catch (MessagingException e) {
            throw new EmailNotSentException(emailMessage.receivers(), e);
        }
    }

    public String processTemplate(Map<String, Object> data, EmailTemplate template) {
        var context = new Context();

        context.setVariables(data);

        return templateEngine.process(template.getPath(), context);
    }
}
