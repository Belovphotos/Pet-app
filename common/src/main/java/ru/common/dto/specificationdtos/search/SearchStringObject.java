package ru.common.dto.specificationdtos.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.common.enums.SearchType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchStringObject {
    private String value;
    private SearchType searchType;
}
