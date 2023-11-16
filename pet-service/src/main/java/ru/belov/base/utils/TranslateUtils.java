package ru.belov.base.utils;

import org.modelmapper.ModelMapper;
import ru.belov.config.ModelMapperSingleton;

public class TranslateUtils<E, D>{

    private final ModelMapper modelMapper;

    private Class<D> dto;
    private Class<E> entity;

    public TranslateUtils(Class<D> dto, Class<E> entity){
        this.dto = dto;
        this.entity = entity;
        modelMapper = ModelMapperSingleton.getInstance();
    }

    public E toEntity(D dto){
        return modelMapper.map(dto, entity);
    }

    public D toDto(E entity){
        return modelMapper.map(entity, dto);
    }
}
