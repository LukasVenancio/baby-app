package com.homebaby.controllers;

import com.homebaby.requests.CreateChildbirthRequest;
import com.homebaby.requests.UpdateChildbirthRequest;
import com.homebaby.responses.childbirth.ChildbirthResponse;
import com.homebaby.usecases.childbirth.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "Parto", description = "Informações sobre os Partos")
public class ChildbirthController {

    private final CreateChildbirthUseCase createChildbirthUseCase;
    private final FindOneChildbirthUseCase findOneChildbirthUseCase;
    private final UpdateChildbirthUseCase updateChildbirthUseCase;
    private final DeleteChildbirthUseCase deleteChildbirthUseCase;
    private final FindByUserIdChildbirthUseCase findByUserIdChildbirthUseCase;

    @PostMapping("/childbirth")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastro de Parto",
            description = "Função responsável pelo cadastro de partos")
    public void create(@RequestBody @Valid CreateChildbirthRequest request) {
        createChildbirthUseCase.execute(request);
    }

    @GetMapping("/childbirth/{id}")
    @Operation(summary = "Listagem de Todos os Partos",
            description = "Função responsável por listar todos os partos de uma gestante")
    public ChildbirthResponse findOne(@PathVariable(value = "id") UUID id){
        return findOneChildbirthUseCase.execute(id);
    }

    @GetMapping("/user/{userId}/childbirth")
    @Operation(summary = "Listagem de Partos por ID do Usuário",
            description = "Função responsável por listar os partos de uma gestante pelo seu ID")
    public List<ChildbirthResponse> findByUserId(@PathVariable(value = "userId") UUID id){
        return findByUserIdChildbirthUseCase.execute(id);
    }

    @PutMapping("/childbirth/{id}")
    @Operation(summary = "Atualização de Parto",
            description = "Função responsável por realizar a atualização do parto de uma gestante")
    public ChildbirthResponse update(@PathVariable(value = "id") UUID id,@RequestBody UpdateChildbirthRequest request){
        return updateChildbirthUseCase.execute(id, request);
    }

    @DeleteMapping("/childbirth/{id}")
    @Operation(summary = "Exclusão de Parto",
            description = "Função responsável por realizar a exclusão do parto de uma gestante")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable(value = "id") UUID id){
        deleteChildbirthUseCase.execute(id);
    }
}
