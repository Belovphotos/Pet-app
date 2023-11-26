package ru.common.dto.specificationdtos.search;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.common.enums.SearchType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchDateObject {
    private LocalDate value;
    private LocalDate valueTo;
    private SearchType searchType;
}
