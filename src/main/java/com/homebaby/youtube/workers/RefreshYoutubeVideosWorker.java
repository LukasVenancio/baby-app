package com.homebaby.youtube.workers;

import com.homebaby.entities.Tag;
import com.homebaby.entities.Video;
import com.homebaby.repositories.tag.TagJpaRepository;
import com.homebaby.repositories.video.VideoJpaRepository;
import com.homebaby.youtube.services.YoutubeSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RefreshYoutubeVideosWorker {
    private final YoutubeSearchService youtubeService;
    private final TagJpaRepository tagJpaRepository;
    private final VideoJpaRepository videoJpaRepository;

    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void execute() {
        var videosResponses = this.youtubeService.searchAllVideos();
        var videosEntities = videosResponses.stream().map(response -> response.toEntity(response)).toList();
        this.refreshVideos(videosEntities);
    }

    private void refreshVideos(List<Video> videos) {
        videos.forEach(video -> {
            var foundVideo = this.videoJpaRepository.findByYoutubeId(video.getYoutubeId());
            foundVideo.ifPresent(value -> video.setId(value.getId()));
            video.setTags(this.mergeTags(video.getTags()));
            this.videoJpaRepository.save(video);
        });
    }

    private Set<Tag> mergeTags(Set<Tag> tags) {
        return tags.stream().map(tag -> {
            var foundTag = this.tagJpaRepository.findByName(tag.getName());
            return foundTag.orElse(tag);
        }).collect(Collectors.toSet());
    }
}
