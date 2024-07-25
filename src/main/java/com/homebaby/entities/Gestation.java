package com.homebaby.entities;

import com.homebaby.enums.Gender;
import com.homebaby.enums.GestationType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "gestations")
public class Gestation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "uuid", insertable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime lastMenstruation;

    @Column(nullable = false)
    private LocalDateTime probableBirthDate;

    @Column(nullable = false)
    private Integer pregnancyNumber;

    @Column(nullable = false)
    private Boolean hadAbortion;

    @Column(nullable = false)
    private Integer abortionNumber;

    @Column(nullable = false)
    private GestationType gestationType;

    private String babyName;

    private Gender babyGender;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Gestation(LocalDateTime lastMenstruation, LocalDateTime probableBirthDate, Integer pregnancyNumber,
                     Boolean hadAbortion, Integer abortionNumber, GestationType gestationType, String babyName, Gender babyGender, User user) {
        this.lastMenstruation = lastMenstruation;
        this.probableBirthDate = probableBirthDate;
        this.pregnancyNumber = pregnancyNumber;
        this.hadAbortion = hadAbortion;
        this.abortionNumber = abortionNumber;
        this.gestationType = gestationType;
        this.babyName = babyName;
        this.babyGender = babyGender;
        this.user = user;
    }
}
