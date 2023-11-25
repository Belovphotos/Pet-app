package ru.belov.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.common.dto.BaseDto;

import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CollarDto extends BaseDto {
    private UUID ownerId;
    private UUID petId;
}
