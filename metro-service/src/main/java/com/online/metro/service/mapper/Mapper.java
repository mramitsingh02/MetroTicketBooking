package com.online.metro.service.mapper;

import java.util.List;

public interface Mapper<DTO, ENTITY> {
    default List<ENTITY> dtosToEntities(List<DTO> dtos) {
        return dtos.stream().map(this::dtoToEntity).toList();
    }

    ENTITY dtoToEntity(DTO dto);

    default List<DTO> entitiesToDTOs(List<ENTITY> entity) {
        return entity.stream().map(this::entityToDTO).toList();
    }

    DTO entityToDTO(ENTITY entity);


}
