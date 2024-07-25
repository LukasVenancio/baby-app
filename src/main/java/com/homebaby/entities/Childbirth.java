package com.homebaby.entities;

import com.homebaby.enums.ChildbirthType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "childbirths")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Childbirth {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "uuid", insertable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private ChildbirthType childbirthType;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private Double height;

    @Column(nullable = false)
    private String intercurrence;

    @Column(nullable = false)
    private Integer apgarFirstMinute;

    @Column(nullable = false)
    private Integer apgarFifthMinute;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Childbirth(
            ChildbirthType childbirthType,
            Double weight,
            Double height,
            String intercurrence,
            Integer apgarFirstMinute,
            Integer apgarFifthMinute,
            User user
    ){
        this.childbirthType = childbirthType;
        this.weight = weight;
        this.height = height;
        this.intercurrence = intercurrence;
        this.apgarFirstMinute = apgarFirstMinute;
        this.apgarFifthMinute = apgarFifthMinute;
        this.user = user;
    }

}
