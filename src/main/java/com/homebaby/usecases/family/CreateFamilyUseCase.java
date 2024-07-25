package com.homebaby.usecases.family;

import com.homebaby.entities.Family;
import com.homebaby.entities.User;
import com.homebaby.errors.ExceptionCode;
import com.homebaby.errors.exceptions.BusinessRuleException;
import com.homebaby.errors.exceptions.EntityNotFoundException;
import com.homebaby.repositories.family.FamilyJpaRepository;
import com.homebaby.repositories.user.UserJpaRepository;
import com.homebaby.requests.CreateFamilyRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateFamilyUseCase {

    private final UserJpaRepository userJpaRepository;
    private final FamilyJpaRepository familyJpaRepository;

    public void execute(CreateFamilyRequest request, User user){
        var family = new Family(
            request.familyType(),
            request.incomeType(),
            user
        );

        familyJpaRepository.save(family);
    }
}
