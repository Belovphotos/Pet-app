package ru.belov.dto.owner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.common.dto.BaseDto;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDto extends BaseDto {
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
}
