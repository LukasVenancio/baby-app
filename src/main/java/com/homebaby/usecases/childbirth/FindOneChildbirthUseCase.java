package com.homebaby.usecases.childbirth;

import com.homebaby.entities.Childbirth;
import com.homebaby.errors.exceptions.EntityNotFoundException;
import com.homebaby.repositories.childbirth.ChildbirthJpaRepository;
import com.homebaby.responses.childbirth.ChildbirthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class FindOneChildbirthUseCase {
    private final ChildbirthJpaRepository childbirthJpaRepository;

    public ChildbirthResponse execute(UUID id){
        log.info("looking for childbirths: {}", id);
        var childbirth = childbirthJpaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Childbirth.class));

        return createChildbirthResponse(childbirth);
    }

    private ChildbirthResponse createChildbirthResponse(Childbirth childbirth){
        return new ChildbirthResponse(
                childbirth.getId(),
                childbirth.getChildbirthType(),
                childbirth.getWeight(),
                childbirth.getHeight(),
                childbirth.getIntercurrence(),
                childbirth.getApgarFirstMinute(),
                childbirth.getApgarFifthMinute()
        );
    }
}
