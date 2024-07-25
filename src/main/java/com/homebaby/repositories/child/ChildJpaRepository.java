package com.homebaby.repositories.child;


import com.homebaby.entities.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChildJpaRepository extends JpaRepository<Child, UUID> { }
