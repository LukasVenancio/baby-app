package com.homebaby.usecases.video;

import com.homebaby.entities.Video;
import com.homebaby.repositories.video.VideoRepository;
import com.homebaby.requests.VideoFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListVideosUseCase {
    private final VideoRepository videoRepository;

    public Page<Video> execute(VideoFilter filter){
        return videoRepository.findByFilter(filter);
    }
}
