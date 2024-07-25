package com.homebaby.repositories.video;

import com.homebaby.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VideoJpaRepository extends JpaRepository<Video, UUID> {
    Optional<Video> findByYoutubeId(String youtubeId);
}
