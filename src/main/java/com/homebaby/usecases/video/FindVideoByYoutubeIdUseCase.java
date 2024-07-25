package com.homebaby.usecases.video;

import com.homebaby.entities.Video;
import com.homebaby.errors.exceptions.EntityNotFoundException;
import com.homebaby.repositories.video.VideoJpaRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindVideoByYoutubeIdUseCase {

    private final VideoJpaRepository videoJpaRepository;

    public Video execute(String youtubeId){
        return videoJpaRepository.findByYoutubeId(youtubeId)
                .orElseThrow(() -> new EntityNotFoundException(Video.class));
    }
}
