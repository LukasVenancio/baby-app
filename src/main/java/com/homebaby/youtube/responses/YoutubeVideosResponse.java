package com.homebaby.youtube.responses;

import com.homebaby.entities.Tag;
import com.homebaby.entities.Video;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class YoutubeVideosResponse {
    private String kind;
    private List<Items> items;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Items {
        private VideoId id;
        private Snippet snippet;
        private Statistics statistics;

        public Video toEntity(Items item){
            var entity = new Video();

            entity.setYoutubeId(item.getId().getId());
            entity.setDescription(item.getSnippet().getDescription().isEmpty() ? null : item.getSnippet().getDescription());
            entity.setPublishedAt(item.getSnippet().getPublishedAt());
            entity.setTitle(item.getSnippet().getTitle());
            entity.setThumbnail(item.getSnippet().getThumbnails().getHigh().getUrl());

            Optional.ofNullable(item.getStatistics().getCommentCount()).ifPresent(
                    value -> entity.setCommentCount(Integer.parseInt(value)));

            Optional.ofNullable(item.getStatistics().getLikeCount()).ifPresent(
                    value -> entity.setLikeCount(Integer.parseInt(value)));

            Optional.ofNullable(item.getStatistics().getViewCount()).ifPresent(
                    value -> entity.setViewCount(Integer.parseInt(value)));

            entity.setTags(item.getSnippet().getTags().stream().map(
                    tagResponse -> new Tag().withName(tagResponse)
                ).collect(Collectors.toSet())
            );

            return entity;
        }

    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Statistics{
        private String viewCount;
        private String likeCount;
        private String commentCount;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class VideoId {
        private String id;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Snippet {
        private LocalDateTime publishedAt;
        private String title;
        private String description;
        private Thumbnail thumbnails;
        private List<String> tags = new ArrayList<>();
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Thumbnail {
        private ThumbnailItem high;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class ThumbnailItem {
        private String url;
    }
}
