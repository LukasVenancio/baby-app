package com.homebaby.usecases.childbirth;

import com.homebaby.entities.Childbirth;
import com.homebaby.entities.User;
import com.homebaby.errors.exceptions.EntityNotFoundException;
import com.homebaby.repositories.childbirth.ChildbirthJpaRepository;
import com.homebaby.repositories.user.UserJpaRepository;
import com.homebaby.requests.CreateChildbirthRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateChildbirthUseCase {
    private final ChildbirthJpaRepository childbirthJpaRepository;
    private final UserJpaRepository userJpaRepository;

    @Transactional
    public void execute(CreateChildbirthRequest request){
        log.info("looking user by userId: {}", request.userId());
        var user = userJpaRepository.findById(request.userId()).orElseThrow(() -> new EntityNotFoundException(User.class));

        var childbirth = mapToEntity(request, user);

        childbirthJpaRepository.save(childbirth);
    }

    private Childbirth mapToEntity(CreateChildbirthRequest request, User user){
        return new Childbirth(
                request.childbirthType(),
                request.weight(),
                request.height(),
                request.intercurrence(),
                request.apgarFirstMinute(),
                request.apgarFifthMinute(),
                user
        );
    }
}
