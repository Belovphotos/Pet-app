package ru.belov.service;

import ru.belov.filter.CollarFilter;
import ru.common.service.BaseService;
import ru.belov.dto.CollarDto;
import ru.belov.entity.CollarEntity;

public interface CollarService extends BaseService<CollarEntity, CollarDto, CollarFilter> {
}
