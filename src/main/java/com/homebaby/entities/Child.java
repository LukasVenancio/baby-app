package com.homebaby.entities;

import com.homebaby.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "children")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "uuid", insertable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime birthDate;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Child(LocalDateTime birthDate, String name, Gender gender, User user){
        this.birthDate = birthDate;
        this.name = name;
        this.gender = gender;
        this.user = user;
    }
}
