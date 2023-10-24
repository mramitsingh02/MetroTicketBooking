package com.online.metro.registration.service.mapper.impl;

import com.online.metro.registration.dto.RouteDTO;
import com.online.metro.registration.entity.Route;
import com.online.metro.registration.service.mapper.RouteMapper;
import org.springframework.stereotype.Component;

@Component
public class RouteMapperImpl implements RouteMapper {
    @Override
    public Route dtoToEntity(final RouteDTO dto) {
        return new Route(dto.getRouteId(), dto.getRouteName(), dto.getRouteNickName(),dto.getCreatedOn(),
                dto.getUpdatedOn());
    }

    @Override
    public RouteDTO entityToDTO(final Route entity) {
        return RouteDTO.builder()
                .routeId(entity.getRouteId())
                .routeName(entity.getRouteName())
                .routeNickName(entity.getRouteNickName())
                .createdOn(entity.getCreatedOn())
                .updatedOn(entity.getUpdatedOn())
                .build();
    }
}
