package com.homebaby.usecases.user;

import com.homebaby.entities.User;
import com.homebaby.errors.ExceptionCode;
import com.homebaby.errors.exceptions.BusinessRuleException;
import com.homebaby.errors.exceptions.EntityNotFoundException;
import com.homebaby.repositories.user.UserJpaRepository;
import com.homebaby.requests.PasswordRecoveryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Component
@Slf4j
public class PasswordRecoveryUseCase {
    private final UserJpaRepository userJpaRepository;
    private final BCryptPasswordEncoder bcryptPasswordEncoder;

    public void execute(UUID id, PasswordRecoveryRequest request){
        var user = userJpaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(User.class));

        validateExceptions(user, request);

        updateUserPassword(user, request);
    }

    public void validateExceptions(User user, PasswordRecoveryRequest request){
        if(!user.getRecoveringPassword()) throw new BusinessRuleException(ExceptionCode.USER_MUST_BE_RECOVERING_PASSWORD);
        if(!request.password().equals(request.passwordConfirmation())) throw new BusinessRuleException(ExceptionCode.PASSWORD_MUST_BE_THE_SAME);
    }

    public void updateUserPassword(User user, PasswordRecoveryRequest request){
        user.setRecoveringPassword(false);

        user.setPassword(bcryptPasswordEncoder.encode(request.password()));

        userJpaRepository.save(user);
    }
}
