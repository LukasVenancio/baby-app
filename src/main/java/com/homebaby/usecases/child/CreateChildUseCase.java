package com.homebaby.usecases.child;

import com.homebaby.entities.Child;
import com.homebaby.entities.User;
import com.homebaby.errors.exceptions.EntityNotFoundException;
import com.homebaby.repositories.child.ChildJpaRepository;
import com.homebaby.repositories.user.UserJpaRepository;
import com.homebaby.requests.CreateChildRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateChildUseCase {
    private final ChildJpaRepository childJpaRepository;

    public void execute(List<CreateChildRequest> request, User user){
        request.forEach(childRequest -> {
            var child = new Child(
                    childRequest.birthDate(),
                    childRequest.name(),
                    childRequest.gender(),
                    user
            );

            childJpaRepository.save(child);
        });
    }
}
