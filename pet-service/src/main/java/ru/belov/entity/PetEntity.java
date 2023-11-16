package ru.belov.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.belov.base.entity.BaseEntity;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(catalog = "postgres", schema = "public", name = "pet_general")
@NoArgsConstructor
@Getter
@Setter
public class PetEntity extends BaseEntity {
//    @Id
//    @GeneratedValue
//    private UUID id;
    private String name;
    private String animalKind;
    private String breed;
    private String gender;
    private LocalDate birthDate;
    private Integer weight;
    private String color;
    private LocalDate lastVisitDate;
    private LocalDate registrationDate;

//    @PrePersist
//    public void prePersist(){
//        this.registrationDate = LocalDate.now();
//    }
}
