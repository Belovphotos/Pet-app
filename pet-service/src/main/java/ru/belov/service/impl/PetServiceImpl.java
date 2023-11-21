package ru.belov.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.belov.base.service.impl.BaseServiceImpl;
import ru.belov.dto.PetDto;
import ru.belov.entity.PetEntity;
import ru.belov.repository.PetRepository;
import ru.belov.service.PetService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Transactional
public class PetServiceImpl extends BaseServiceImpl<PetEntity, PetDto> implements PetService {
    private final PetRepository petRepository;
    private final ModelMapper modelMapper;

    public PetServiceImpl(PetRepository repository, ModelMapper modelMapper) {
        super(repository);
        this.petRepository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PetDto> findAllPetsByOwner(UUID id) {
        return petRepository.findAllByOwnerId(id)
                .stream()
                .map(e -> modelMapper
                        .map(e, PetDto.class))
                .collect(Collectors.toList());
    }
}
