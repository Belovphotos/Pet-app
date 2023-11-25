package ru.belov.dto.owner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.belov.dto.PetDto;

import java.time.LocalDate;
import java.util.Set;
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
