package com.homebaby.repositories.childbirth;

import com.homebaby.entities.Childbirth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChildbirthJpaRepository  extends JpaRepository<Childbirth, UUID> {
    Optional<List<Childbirth>> findAllByUserId(UUID userId);
}
