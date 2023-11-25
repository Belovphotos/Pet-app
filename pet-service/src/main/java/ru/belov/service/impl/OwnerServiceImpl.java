package ru.belov.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.common.service.impl.BaseServiceImpl;
import ru.belov.dto.PetDto;
import ru.belov.dto.owner.FullOwnerDto;
import ru.belov.dto.owner.OwnerDto;
import ru.belov.entity.OwnerEntity;
import ru.belov.repository.OwnerRepository;
import ru.belov.service.OwnerService;
import ru.belov.service.PetService;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class OwnerServiceImpl extends BaseServiceImpl<OwnerEntity, OwnerDto> implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final PetService petService;
    private final ModelMapper modelMapper;

    public OwnerServiceImpl(OwnerRepository repository, PetService petService, ModelMapper modelMapper) {
        super(repository);
        this.ownerRepository = repository;
        this.petService = petService;
        this.modelMapper = modelMapper;
    }

    @Override
    public FullOwnerDto findAllOwnersWithPets(UUID ownerId) {
        FullOwnerDto result;
        OwnerEntity owner = ownerRepository.findById(ownerId).orElse(null);
        if (owner != null){
           result = modelMapper.map(owner, FullOwnerDto.class);
            Set<PetDto> pets = new HashSet<>(petService.findAllPetsByOwner(ownerId));
            result.setPets(pets);
        } else {
            return null;
        }
        return result;
    }
}
