package com.homebaby.youtube.clients;

import com.homebaby.youtube.responses.YoutubeSearchResponse;
import com.homebaby.youtube.responses.YoutubeVideosResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "YoutubeApiClient", url = "${youtube.api-url}")
public interface YoutubeApiFeignClient {

    @GetMapping(value = "/search/?maxResults=100&key={key}&channelId={channelId}&pageToken={pageToken}")
    YoutubeSearchResponse searchAllVideos(
            @RequestParam String key,
            @RequestParam String channelId,
            @RequestParam String pageToken
    );

    @GetMapping(value = "/videos/?part=snippet,id,statistics&key={key}&id={id}")
    YoutubeVideosResponse searchVideosById(
            @PathVariable("key") String key,
            @PathVariable("id") String id
    );

}
