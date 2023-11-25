package ru.common.filter;

import org.springframework.data.jpa.domain.Specification;

@FunctionalInterface
public interface Filter {
    Specification toSpecification();
}
