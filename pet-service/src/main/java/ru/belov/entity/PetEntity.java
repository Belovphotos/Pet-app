package ru.belov.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.common.entity.BaseEntity;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(catalog = "postgres", schema = "public", name = "pet_general")
@NoArgsConstructor
@Getter
@Setter
public class PetEntity extends BaseEntity {
    private String name;
    private String animalKind;
    private String breed;
    private String gender;
    private LocalDate birthDate;
    private Integer weight;
    private String color;
    private LocalDate lastVisitDate;
    private LocalDate registrationDate;
    private UUID cheapId;
    private UUID collarId;
    private UUID ownerId;
}
