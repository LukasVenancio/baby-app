package com.homebaby.usecases;

import com.homebaby.entities.User;
import com.homebaby.enums.EmailTemplate;
import com.homebaby.errors.exceptions.EntityNotFoundException;
import com.homebaby.repositories.user.UserJpaRepository;
import com.homebaby.requests.EmailMessage;
import com.homebaby.requests.RequirePasswordRecoveryRequest;
import com.homebaby.services.RandomPasswordService;
import com.homebaby.services.SmtpEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class RequirePasswordRecoveryUseCase {

    private final UserJpaRepository userJpaRepository;
    private final RandomPasswordService randomPasswordService;
    private final SmtpEmailService smtpEmailService;
    private final BCryptPasswordEncoder BCryptPasswordEncoder;

    public void execute(RequirePasswordRecoveryRequest request){
        var user = userJpaRepository.findByEmail(request.email())
                .orElseThrow(() -> new EntityNotFoundException(User.class));

        var newPassword = randomPasswordService.generate();

        updateUser(user, newPassword);

        sendEmail(newPassword, user.getEmail());
    }

    private void updateUser(User user, String newPassword){
        user.setRecoveringPassword(true);
        user.setPassword(BCryptPasswordEncoder.encode(newPassword));
        userJpaRepository.save(user);
    }

    private void sendEmail(String newPassword, String receiver){
        Map<String, Object> data = new HashMap<>();
        data.put("password", newPassword);

        var template = smtpEmailService.processTemplate(data, EmailTemplate.FORGOT_PASSWORD);
        log.info("Generating template: {}", template);

        var emailMessage =
                new EmailMessage(
                        List.of(receiver),
                        "Alteração de senha",
                        template
                );

        smtpEmailService.sendEmail(emailMessage);
    }

}
