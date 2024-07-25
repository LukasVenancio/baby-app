package com.homebaby.usecases.user;

import com.homebaby.entities.Child;
import com.homebaby.entities.Family;
import com.homebaby.entities.Gestation;
import com.homebaby.entities.User;
import com.homebaby.errors.exceptions.EntityNotFoundException;
import com.homebaby.repositories.user.UserJpaRepository;
import com.homebaby.responses.child.ChildSimpleResponse;
import com.homebaby.responses.family.FamilyResponse;
import com.homebaby.responses.gestation.GestationSimpleResponse;
import com.homebaby.responses.user.UserDetailedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindUserByIdUseCase {
    private final UserJpaRepository userJpaRepository;

    public UserDetailedResponse execute(UUID id){
        var user = userJpaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(User.class));


        return new UserDetailedResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthDate(),
                user.getEmail(),
                user.getMaritalStatus(),
                mapFamilyToResponse(user.getFamily()),
                mapGestationToResponse(user.getGestations()),
                mapChildToResponse(user.getChildren())
        );
    }

    private FamilyResponse mapFamilyToResponse(Family family){
        return new FamilyResponse(
                family.getId(),
                family.getFamilyType(),
                family.getIncomeType()
        );
    }

    private List<GestationSimpleResponse> mapGestationToResponse(List<Gestation> gestations){
        return gestations.stream()
                .map(gestation -> new GestationSimpleResponse(
                        gestation.getId(),
                        gestation.getProbableBirthDate()
                )).collect(Collectors.toList());

    }

    private List<ChildSimpleResponse> mapChildToResponse(List<Child> children){
        return children.stream()
                .map(child -> new ChildSimpleResponse(
                        child.getId(),
                        child.getName(),
                        child.getBirthDate()
                )).collect(Collectors.toList());
    }
}
