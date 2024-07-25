package com.homebaby.controllers;

import com.homebaby.requests.EmailMessage;
import com.homebaby.requests.SendTestEmailWithTemplateRequest;
import com.homebaby.services.SmtpEmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/email")
@Tag(name = "Email", description = "Informações sobre o Email")
public class MailController {
    private final SmtpEmailService emailService;

    @Profile("test")
    @PostMapping("/send-test-email")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Envio de Email Teste",
            description = "Função responsável por realizar o envio de email de teste para fins de desenvolvimento")
    public void sendTestEmail(@RequestBody @Valid EmailMessage request) {
        emailService.sendEmail(request);
    }

    @Profile("test")
    @PostMapping("/send-test-email-with-template")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Envio de Email",
            description = "Função responsável pelo envio de email com seu respectivo modelo de email")
    public void sendTestEmailWithTemplate(@RequestBody @Valid SendTestEmailWithTemplateRequest request) {
        var emailMessage = new EmailMessage(
                request.receiver(),
                request.subject(),
                emailService.processTemplate(
                        request.data(),
                        request.template()
                )
        );
        emailService.sendEmail(emailMessage);
    }
}
