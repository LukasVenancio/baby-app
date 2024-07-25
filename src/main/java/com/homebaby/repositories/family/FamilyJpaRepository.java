package com.homebaby.repositories.family;

import com.homebaby.entities.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FamilyJpaRepository extends JpaRepository<Family, UUID> {
    Optional<Family> findByUserId(UUID userId);
}
