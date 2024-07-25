package com.homebaby.controllers;

import com.homebaby.entities.User;
import com.homebaby.requests.CreateUserRequest;
import com.homebaby.requests.PasswordRecoveryRequest;
import com.homebaby.responses.user.UserDetailedResponse;
import com.homebaby.usecases.user.CreateUserUseCase;
import com.homebaby.usecases.user.FindUserByIdUseCase;
import com.homebaby.usecases.user.PasswordRecoveryUseCase;
import com.homebaby.usecases.user.ValidateEmailUseCase;
import com.homebaby.youtube.responses.YoutubeVideosResponse;
import com.homebaby.youtube.services.YoutubeSearchService;
import com.homebaby.youtube.workers.RefreshYoutubeVideosWorker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Informações sobre o Usuário")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final PasswordRecoveryUseCase passwordRecoveryUseCase;
    private final FindUserByIdUseCase findUserByIdUseCase;
    private final ValidateEmailUseCase validateEmailUseCase;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastro de usuário",
            description = "Função responsável pelo cadastro de usuários")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = User.class))
            })
    })
    @SecurityRequirement(name = "jwt_auth")
    public void create(@RequestBody @Valid CreateUserRequest request) {
        createUserUseCase.execute(request);
    }

    @PutMapping(value = "/{id}/password-recovery")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Recuperação de Senha",
            description = "Função responsável pela recuperação do usuário")
    public void passwordRecovery(@PathVariable(value = "id") UUID id, @RequestBody @Valid PasswordRecoveryRequest request) {
        passwordRecoveryUseCase.execute(id, request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Listagem de informações",
            description = "Função responsável por listar as informações do usuário")
    public UserDetailedResponse findOne(@PathVariable(value = "id") UUID id) {
        return findUserByIdUseCase.execute(id);
    }

    @GetMapping("/{id}/validate-email")
    public String validateEmail(@PathVariable(value = "id") UUID id){
        return this.validateEmailUseCase.execute(id);
    }
}
