package ru.common.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.common.dto.BaseDto;
import ru.common.entity.BaseEntity;

import java.util.List;
import java.util.UUID;

public interface BaseService<E extends BaseEntity, D extends BaseDto> {
    UUID update(D entity);
    UUID save(D entity);
    void saveAll(List<D> entities);
    Page<D> findAllPageable(Pageable pageable);
    List<D> findAll();

    D findById(UUID id);

    void delete(UUID id);
}
