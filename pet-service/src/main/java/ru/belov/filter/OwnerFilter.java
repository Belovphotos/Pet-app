package ru.belov.filter;

import lombok.*;
import org.springframework.data.jpa.domain.Specification;
import ru.belov.entity.OwnerEntity;
import ru.common.filter.Filter;

@NoArgsConstructor
@Getter
@Setter
@Builder
public class OwnerFilter implements Filter<OwnerEntity> {
    @Override
    public Specification<OwnerEntity> toSpecification() {
        return null;
    }
}
