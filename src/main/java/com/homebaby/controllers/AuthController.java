package com.homebaby.controllers;

import com.homebaby.requests.LoginRequest;
import com.homebaby.requests.RequirePasswordRecoveryRequest;
import com.homebaby.responses.LoginResponse;
import com.homebaby.usecases.LoginUseCase;
import com.homebaby.usecases.RequirePasswordRecoveryUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {

    private final LoginUseCase loginUseCase;
    private final RequirePasswordRecoveryUseCase requirePasswordRecoveryUseCase;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request){
        return new LoginResponse(loginUseCase.execute(request));
    }

    @PatchMapping("/require-password-recovery")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void requirePasswordRecovery(@RequestBody @Valid RequirePasswordRecoveryRequest request){
        requirePasswordRecoveryUseCase.execute(request);
    }
}