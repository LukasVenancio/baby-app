package com.homebaby.usecases.childbirth;

import com.homebaby.entities.Childbirth;
import com.homebaby.errors.exceptions.EntityNotFoundException;
import com.homebaby.repositories.childbirth.ChildbirthJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class DeleteChildbirthUseCase {

    private final ChildbirthJpaRepository childbirthJpaRepository;

    public void execute(UUID id){
        log.info("looking for childbirths: {}", id);
        var childbirth = childbirthJpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Childbirth.class));

        childbirthJpaRepository.delete(childbirth);
    }
}
