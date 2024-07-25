package com.homebaby.usecases.childbirth;

import com.homebaby.entities.Childbirth;
import com.homebaby.entities.User;
import com.homebaby.errors.exceptions.EntityNotFoundException;
import com.homebaby.repositories.childbirth.ChildbirthJpaRepository;
import com.homebaby.repositories.user.UserJpaRepository;
import com.homebaby.responses.childbirth.ChildbirthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindByUserIdChildbirthUseCase {
    private final ChildbirthJpaRepository childbirthJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public List<ChildbirthResponse> execute(UUID userId){
        var user = userJpaRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(User.class));

        var childbirths = childbirthJpaRepository.findAllByUserId(user.getId()).orElseThrow(() -> new EntityNotFoundException(Childbirth.class));

        return childbirths.stream().map(childbirth -> new ChildbirthResponse(
                childbirth.getId(),
                childbirth.getChildbirthType(),
                childbirth.getWeight(),
                childbirth.getHeight(),
                childbirth.getIntercurrence(),
                childbirth.getApgarFirstMinute(),
                childbirth.getApgarFifthMinute()
        )).collect(Collectors.toList());
    }
}
