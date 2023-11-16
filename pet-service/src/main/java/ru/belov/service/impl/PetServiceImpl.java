package ru.belov.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.belov.base.service.BaseService;
import ru.belov.base.service.impl.BaseServiceImpl;
import ru.belov.dto.PetDto;
import ru.belov.entity.PetEntity;
import ru.belov.repository.PetRepository;
import ru.belov.service.PetService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
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

//    @Override
//    public UUID save(PetDto entity) {
//        return repository.save(modelMapper.map(entity, PetEntity.class)).getId();
//    }
//
//    @Override
//    public PetDto findById(UUID id) {
//        return repository.findById(id)
//                .map(petEntity -> modelMapper.map(petEntity, PetDto.class))
//                .orElse(null);
//    }
//
//    @Override
//    public UUID update(PetDto entity) {
//        return save(entity);
//    }
//
//    @Override
//    public void delete(UUID id) {
//        try {
//            repository.deleteById(id);
//        } catch (NoSuchElementException e){
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public List<PetDto> findAll() {
//        return repository.findAll()
//                .stream()
//                .map(e -> modelMapper.map(e, PetDto.class))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public Page<PetDto> findAllPageable(Pageable pageable) {
//        return new PageImpl<>(findAll(), pageable, pageable.getPageSize());
//    }
//
//    @Override
//    public void saveAll(List<PetDto> entities) {
//        repository.saveAll(entities
//                .stream()
//                .map(d-> modelMapper.map(d, PetEntity.class))
//                .collect(Collectors.toList()));
//    }
}
