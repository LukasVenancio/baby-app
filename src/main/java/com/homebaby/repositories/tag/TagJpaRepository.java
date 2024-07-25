package com.homebaby.repositories.tag;

import com.homebaby.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TagJpaRepository extends JpaRepository<Tag, UUID> {
    Optional<Tag> findByName(String name);
}
