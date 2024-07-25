package com.homebaby.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name = "tags")
@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;
}
