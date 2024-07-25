package com.homebaby.repositories.gestation;

import com.homebaby.entities.Gestation;
import com.homebaby.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GestationJpaRepository extends JpaRepository<Gestation, UUID> {
    Optional<Gestation> findByUser(User user);
}
