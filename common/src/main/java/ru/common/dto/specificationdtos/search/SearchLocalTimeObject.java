package ru.common.dto.specificationdtos.search;

import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.atc.enums.SearchType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchLocalTimeObject {
    private LocalTime value;
    private LocalTime valueTo;
    private SearchType searchType;
}
