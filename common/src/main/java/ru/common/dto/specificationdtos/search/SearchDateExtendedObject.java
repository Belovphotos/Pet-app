package ru.common.dto.specificationdtos.search;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchDateExtendedObject extends SearchDateObject {
    private LocalDate valueTo;
}
