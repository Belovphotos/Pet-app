package ru.belov.filter;

import lombok.*;
import org.springframework.data.jpa.domain.Specification;
import ru.belov.entity.CollarEntity;
import ru.common.filter.Filter;
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CollarFilter implements Filter<CollarEntity> {
    @Override
    public Specification<CollarEntity> toSpecification() {
        return null;
    }
}
