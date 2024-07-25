package com.homebaby.usecases.user;

import com.homebaby.entities.User;
import com.homebaby.enums.EmailTemplate;
import com.homebaby.errors.exceptions.DuplicatedResourceException;
import com.homebaby.repositories.user.UserJpaRepository;
import com.homebaby.requests.CreateUserRequest;
import com.homebaby.requests.EmailMessage;
import com.homebaby.services.SmtpEmailService;
import com.homebaby.usecases.child.CreateChildUseCase;
import com.homebaby.usecases.family.CreateFamilyUseCase;
import com.homebaby.usecases.gestation.CreateGestationUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateUserUseCase {

    private final UserJpaRepository userJpaRepository;
    private final CreateFamilyUseCase createFamilyUseCase;
    private final CreateChildUseCase createChildUseCase;
    private final CreateGestationUseCase createGestationUseCase;
    private final SmtpEmailService smtpEmailService;
    @Value("${home-care-baby.base-url}")
    private String baseUrl;

    @Transactional
    public void execute(CreateUserRequest request){
        if (this.userJpaRepository.findByEmail(request.email()).isPresent()) throw new DuplicatedResourceException(User.class);

        var encryptedPassword= new BCryptPasswordEncoder().encode(request.password());

        var user = new User(
                request.firstName(),
                request.lastName(),
                request.email(),
                encryptedPassword,
                request.maritalStatus(),
                request.birthDate()
        );

        log.warn("Creating user with email: {} ", request.email());
        var createdUser = userJpaRepository.save(user);

        createFamilyUseCase.execute(request.family(), createdUser);
        createChildUseCase.execute(request.children(), createdUser);
        createGestationUseCase.execute(request.gestations(), createdUser);

        sendEmailToValidation(createdUser);
    }

    private void sendEmailToValidation(User user){
        Map<String, Object> data = new HashMap<>();
        data.put("link", generateUrlToValidateEmail(user.getId()));

        var template = smtpEmailService.processTemplate(data, EmailTemplate.EMAIL_VALIDATION);

        var emailMessage =
                new EmailMessage(
                        List.of(user.getEmail()),
                        "Validação de E-mail",
                        template
                );

        smtpEmailService.sendEmail(emailMessage);
    }

    private String generateUrlToValidateEmail(UUID userId){
        return MessageFormat.format(
                "{0}/user/{1}/validate-email",
                baseUrl,
                userId
        );
    }
}
