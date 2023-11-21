package ru.belov.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.belov.base.service.impl.BaseServiceImpl;
import ru.belov.dto.CollarDto;
import ru.belov.entity.CollarEntity;
import ru.belov.repository.CollarRepository;
import ru.belov.service.CollarService;

@Service
@Transactional
public class CollarServiceImpl extends BaseServiceImpl<CollarEntity, CollarDto> implements CollarService {
    private final CollarRepository collarRepository;

    public CollarServiceImpl(CollarRepository repository) {
        super(repository);
        this.collarRepository = repository;
    }
}
