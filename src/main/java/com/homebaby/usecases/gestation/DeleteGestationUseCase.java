package com.homebaby.usecases.gestation;

import com.homebaby.entities.Gestation;
import com.homebaby.errors.exceptions.EntityNotFoundException;
import com.homebaby.repositories.gestation.GestationJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class DeleteGestationUseCase {

    private final GestationJpaRepository gestationJpaRepository;

    public void execute(UUID id) {
        log.info("looking for gestations: {}", id);
        var gestation = gestationJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Gestation.class));

        gestationJpaRepository.delete(gestation);
    }
}