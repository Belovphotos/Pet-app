package ru.common.dto;

import com.codiform.moo.annotation.Optionality;
import com.codiform.moo.annotation.Property;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BaseDto implements Serializable {

    @Property(optionality = Optionality.OPTIONAL)
    protected UUID id;

    @Property(optionality = Optionality.OPTIONAL)
    protected LocalDate registrationDate;
}
