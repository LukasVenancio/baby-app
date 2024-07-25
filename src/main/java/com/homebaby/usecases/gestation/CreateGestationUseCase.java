package com.homebaby.usecases.gestation;

import com.homebaby.entities.Gestation;
import com.homebaby.entities.User;
import com.homebaby.errors.exceptions.EntityNotFoundException;
import com.homebaby.repositories.gestation.GestationJpaRepository;
import com.homebaby.repositories.user.UserJpaRepository;
import com.homebaby.requests.CreateGestationRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateGestationUseCase {
    private final GestationJpaRepository gestationRepository;

    private final UserJpaRepository userJpaRepository;

    private final int durationOfGestationInWeeks = 40;

    private final Duration durationOfFortyWeeks = Duration.ofDays(durationOfGestationInWeeks * 7);

    @Transactional
    public void execute(List<CreateGestationRequest> request, User user) {
        request.forEach(gestationRequest -> {
            var gestation = new Gestation(
                    gestationRequest.lastMenstruation(),
                    gestationRequest.lastMenstruation().plus(durationOfFortyWeeks),
                    gestationRequest.pregnancyNumber(),
                    gestationRequest.hadAbortion(),
                    gestationRequest.abortionNumber(),
                    gestationRequest.gestationType(),
                    gestationRequest.babyName(),
                    gestationRequest.babyGender(),
                    user
            );

            gestationRepository.save(gestation);
        });
    }
}
