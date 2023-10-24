package com.online.metro.registration.service.mapper;

import com.online.metro.registration.dto.RouteDTO;
import com.online.metro.registration.entity.Route;

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
