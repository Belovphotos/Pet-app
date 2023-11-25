package ru.belov.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.common.service.BaseService;
import ru.belov.dto.PetDto;
import ru.belov.entity.PetEntity;

import java.util.List;
import java.util.UUID;

public interface PetService extends BaseService<PetEntity, PetDto> {
    UUID save(PetDto entity);

    PetDto findById(UUID id);

    UUID update(PetDto entity);

    void delete(UUID id);

    List<PetDto> findAll();

    Page<PetDto> findAllPageable(Pageable pageable);

    void saveAll(List<PetDto> entities);

    List<PetDto> findAllPetsByOwner(UUID id);
}
