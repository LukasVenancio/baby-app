package com.homebaby.usecases.gestation;

import com.homebaby.entities.Gestation;
import com.homebaby.entities.User;
import com.homebaby.errors.exceptions.EntityNotFoundException;
import com.homebaby.repositories.gestation.GestationJpaRepository;
import com.homebaby.repositories.user.UserJpaRepository;
import com.homebaby.responses.gestation.GestationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindGestationByUserIdUseCase {
    private final GestationJpaRepository gestationJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public GestationResponse execute(UUID userId) {
        var user = userJpaRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(User.class));
        var gestation = gestationJpaRepository
                .findByUser(user).orElseThrow(() -> new EntityNotFoundException(Gestation.class));
        return new GestationResponse(
                gestation.getId(),
                gestation.getLastMenstruation(),
                gestation.getProbableBirthDate(),
                gestation.getPregnancyNumber(),
                gestation.getHadAbortion(),
                gestation.getAbortionNumber(),
                gestation.getGestationType(),
                gestation.getBabyName(),
                gestation.getBabyGender()
        );
    }
}
