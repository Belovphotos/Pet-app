package ru.common.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.common.dto.BaseDto;
import ru.common.entity.BaseEntity;
import ru.common.filter.Filter;

import java.util.List;
import java.util.UUID;

public interface BaseService<E extends BaseEntity, D extends BaseDto, F extends Filter<E>> {
    UUID update(D entity);
    UUID save(D entity);
    void saveAll(List<D> entities);
    Page<D> findAllPageable(Pageable pageable);
    List<D> findAll();

    Page<D> findAllByFilter(F search, Pageable pageable);

    D findById(UUID id);

    void delete(UUID id);
}
