package com.homebaby.youtube.services;

import com.homebaby.youtube.clients.YoutubeApiFeignClient;
import com.homebaby.youtube.responses.YoutubeSearchResponse;
import com.homebaby.youtube.responses.YoutubeVideosResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class YoutubeSearchService {
    @Value("${youtube.key}")
    private String key;
    @Value("${youtube.channel-id}")
    private String channelId;

    private final YoutubeApiFeignClient feignClient;

    private List<String> getAllVideosIds() {
        var hasNextPage = true;
        String nextPageToken = null;
        List<YoutubeSearchResponse.Items> items = new ArrayList<>();

        while (hasNextPage) {
            var response = feignClient.searchAllVideos(this.key, this.channelId, nextPageToken);

            if (Objects.isNull(response.getNextPageToken())) hasNextPage = false;
            nextPageToken = response.getNextPageToken();
            items.addAll(response.getItems());
        }

      return this.getIds(items);
    }

    private List<String> getIds(List<YoutubeSearchResponse.Items> items){
        return items.stream().map(item -> item.getId().getVideoId()).collect(Collectors.toList());
    }

    public List<YoutubeVideosResponse.Items> searchAllVideos(){
        var formattedIdsList = this.formatIdsToStringList(this.getAllVideosIds());
        var response = new ArrayList<YoutubeVideosResponse.Items>();

        formattedIdsList.forEach(idsList -> response.addAll(
                this.feignClient.searchVideosById(this.key, idsList).getItems())
        );

        return response;
    }

    private List<String> formatIdsToStringList(List<String> ids) {
        var formattedIdsList = new ArrayList<String>();
        StringBuilder formattedId;
        int itemsLimitPerRequest = 50;
        var subListsSize = Math.ceilDiv(ids.size(), itemsLimitPerRequest);

        for(
            var subListsCount = 0;
            subListsCount < subListsSize * itemsLimitPerRequest;
            subListsCount+= itemsLimitPerRequest
        ){
            var sublist = ids.subList(subListsCount, Math.min(subListsCount + 50, ids.size()));
            formattedId = new StringBuilder();

            for (var count = 0; count < sublist.size(); count++) {
                if (sublist.get(count) != null) {
                    formattedId.append(sublist.get(count));

                    if (count != sublist.size() - 1) formattedId.append(",");
                }
            }
            formattedIdsList.add(formattedId.toString());
        }
        return formattedIdsList;
    }
}
