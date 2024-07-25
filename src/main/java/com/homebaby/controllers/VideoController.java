package com.homebaby.controllers;

import com.homebaby.entities.Video;
import com.homebaby.requests.VideoFilter;
import com.homebaby.usecases.video.FindVideoByYoutubeIdUseCase;
import com.homebaby.usecases.video.ListVideosUseCase;
import com.homebaby.youtube.workers.RefreshYoutubeVideosWorker;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/video")
@RequiredArgsConstructor
public class VideoController {
    private final ListVideosUseCase listVideosUseCase;
    private final RefreshYoutubeVideosWorker worker;
    private final FindVideoByYoutubeIdUseCase findVideoByYoutubeIdUseCase;

    @GetMapping()
    public ResponseEntity<List<Video>> list(@ParameterObject VideoFilter filter) {
        final var page = listVideosUseCase.execute(filter);
        return ResponseEntity
                .ok()
                .header("total-pages", String.valueOf(page.getTotalPages()))
                .header("total-elements", String.valueOf(page.getTotalElements()))
                .body(page.getContent());
    }

    @Profile("test")
    @PostMapping(value = "/refresh")
    public void refresh() {
        worker.execute();
    }

    @GetMapping("/{youtubeId}")
    public Video findVideoByYoutubeId(@PathVariable String youtubeId){
        return findVideoByYoutubeIdUseCase.execute(youtubeId);
    }

}
