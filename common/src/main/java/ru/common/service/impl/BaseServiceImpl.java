package ru.common.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import ru.common.dto.BaseDto;
import ru.common.entity.BaseEntity;
import ru.common.filter.Filter;
import ru.common.repository.JpaBaseRepository;
import ru.common.service.BaseService;
import ru.common.utils.TranslateUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;
@RequiredArgsConstructor
public class BaseServiceImpl<E extends BaseEntity, D extends BaseDto, F extends Filter<E>> implements BaseService<E,D, F> {

    protected JpaBaseRepository<E, UUID> repository;
    private final TranslateUtils<E, D> translateUtils = new TranslateUtils<>((Class<D>) getGenericClassType(1), (Class<E>) getGenericClassType(0));

    public BaseServiceImpl(JpaBaseRepository<E, UUID> repository) {
        this.repository = repository;
    }

    private Type getGenericClassType(int index){
        Type type = getClass().getGenericSuperclass();
        while (!(type instanceof ParameterizedType)){
            type = ((Class<?>) type).getGenericSuperclass();
        }
        return ((ParameterizedType) type).getActualTypeArguments()[index];
    }

    @Override
    public UUID save(D dto) {
        return repository.save(translateUtils.toEntity(dto)).getId();
    }

    @Override
    public UUID update(D dto) {
        return save(dto);
    }

    @Override
    public void saveAll(List<D> dtos) {
        repository.saveAll(dtos
                .stream()
                .map(translateUtils::toEntity)
                .collect(Collectors.toList()));
    }

    @Override
    public Page<D> findAllPageable(Pageable pageable) {
        return new PageImpl<>(findAll(), pageable, pageable.getPageSize());
    }

    @Override
    public List<D> findAll() {
        return repository.findAll()
                .stream()
                .map(translateUtils::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<D> findAllByFilter(F search, Pageable pageable) {
        return null;
    }

    @Override
    public D findById(UUID id) {
        return translateUtils.toDto(repository.findEntityById(id).orElse(null));
    }

    @Override
    public void delete(UUID id) {
        try {
            repository.deleteById(id);
        } catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }
}
