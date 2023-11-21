package ru.belov.dto;

import lombok.*;
import ru.belov.base.dto.BaseDto;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PetDto extends BaseDto {
    private String name;
    private String animalKind;
    private String breed;
    private String gender;
    private LocalDate birthDate;
    private Integer weight;
    private String color;
    private LocalDate lastVisitDate;
    private UUID cheapId;
    private UUID collarId;
    private UUID ownerId;
}
