package com.homebaby.usecases.childbirth;

import com.homebaby.entities.Childbirth;
import com.homebaby.errors.exceptions.EntityNotFoundException;
import com.homebaby.repositories.childbirth.ChildbirthJpaRepository;
import com.homebaby.requests.UpdateChildbirthRequest;
import com.homebaby.responses.childbirth.ChildbirthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateChildbirthUseCase {

    private final ChildbirthJpaRepository childbirthJpaRepository;

    public ChildbirthResponse execute(UUID id, UpdateChildbirthRequest request){
        log.info("looking childbirth by id: {}", id);
        var childbirth = childbirthJpaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Childbirth.class));
        log.info("updating fields of request");
        updateFields(childbirth, request);

        var updatedChildbirth = childbirthJpaRepository.save(childbirth);
        return createChildbirthResponse(updatedChildbirth);
    }

    private void updateFields(Childbirth childbirth, UpdateChildbirthRequest request){
        childbirth.setChildbirthType(request.childbirthType());
        childbirth.setWeight(request.weight());
        childbirth.setHeight(request.height());
        childbirth.setIntercurrence(request.intercurrence());
        childbirth.setApgarFirstMinute(request.apgarFirstMinute());
        childbirth.setApgarFifthMinute(request.apgarFifthMinute());
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
