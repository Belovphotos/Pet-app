package ru.belov.service;

import ru.common.service.BaseService;
import ru.belov.dto.owner.FullOwnerDto;
import ru.belov.dto.owner.OwnerDto;
import ru.belov.entity.OwnerEntity;

import java.util.UUID;

public interface OwnerService extends BaseService<OwnerEntity, OwnerDto> {

    FullOwnerDto findAllOwnersWithPets(UUID ownerId);

}
