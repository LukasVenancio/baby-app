package com.homebaby.entities;

import com.homebaby.enums.FamilyType;
import com.homebaby.enums.IncomeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "families")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "uuid", insertable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private FamilyType familyType;

    @Column(nullable = false)
    private IncomeType incomeType;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Family(FamilyType familyType, IncomeType incomeType, User user){
        this.familyType = familyType;
        this.incomeType = incomeType;
        this.user = user;
    }
}
