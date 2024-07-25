package com.homebaby.youtube.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class YoutubeSearchResponse {

    private String nextPageToken;
    private List<Items> items;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Items {
        private VideoId id;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class VideoId {
        private String videoId;
    }
}


