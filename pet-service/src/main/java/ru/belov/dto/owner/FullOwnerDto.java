package ru.belov.dto.owner;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.belov.dto.PetDto;
import ru.belov.entity.PetEntity;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FullOwnerDto {
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private LocalDate registrationDate;
    private Set<PetDto> pets;
}
