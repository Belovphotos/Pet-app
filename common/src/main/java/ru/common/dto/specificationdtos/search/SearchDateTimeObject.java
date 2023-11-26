package ru.common.dto.specificationdtos.search;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.atc.enums.SearchType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchDateTimeObject {

    private LocalDateTime value;

    private SearchType searchType;
}