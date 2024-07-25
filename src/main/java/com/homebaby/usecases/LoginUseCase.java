package com.homebaby.usecases;

import com.homebaby.errors.ExceptionCode;
import com.homebaby.errors.exceptions.BusinessRuleException;
import com.homebaby.requests.LoginRequest;
import com.homebaby.security.dto.UserDetailsDTO;
import com.homebaby.security.services.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LoginUseCase {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    public String execute(LoginRequest request){
        var usernamePassword = new UsernamePasswordAuthenticationToken(request.email().trim(), request.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var userDetails = (UserDetailsDTO) auth.getPrincipal();

        if (Objects.isNull(userDetails.getUser().getEmailValidatedAt())) throw new BusinessRuleException(ExceptionCode.EMAIL_NOT_VALIDATED);

        return this.jwtTokenService.generateToken(userDetails);
    }
}
