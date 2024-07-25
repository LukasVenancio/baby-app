package com.homebaby.controllers;

import com.homebaby.responses.gestation.GestationResponse;
import com.homebaby.usecases.gestation.DeleteGestationUseCase;
import com.homebaby.usecases.gestation.FindGestationByUserIdUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/gestation")
@RequiredArgsConstructor
@Tag(name = "Gestação", description = "Informações de Gestação")
public class GestationController {
    private final DeleteGestationUseCase deleteGestationUseCase;
    private final FindGestationByUserIdUseCase findGestationByUserIdUseCase;

    @GetMapping("/{id}")
    @Operation(summary = "Cadastro de gestação",
            description = "Função responsável pelo cadastro de gestações")
    public GestationResponse findOne(@PathVariable(value = "id") UUID id){
        return findGestationByUserIdUseCase.execute(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Exclusão de gestação",
            description = "Função responsável pela exclusão de gestações")
    public void delete(@PathVariable(value = "id") UUID id){
        deleteGestationUseCase.execute(id);
    }
}
