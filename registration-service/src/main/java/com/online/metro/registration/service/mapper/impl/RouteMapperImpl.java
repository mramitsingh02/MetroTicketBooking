package com.online.metro.registration.service.mapper.impl;

import com.online.metro.registration.dto.RouteDTO;
import com.online.metro.registration.entity.Route;
import com.online.metro.registration.service.mapper.RouteMapper;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class RouteMapperImpl implements RouteMapper {
    @Override
    public Route dtoToEntity(final RouteDTO dto) {
        return Route.builder()
                .routeId(dto.getRouteId())
                .routeName(dto.getRouteName())
                .routeNickName(dto.getRouteNickName())
                .createdOn(dto.getCreatedOn())
                .updatedOn(dto.getUpdatedOn())
                .build();
    }

    @Override
    public RouteDTO entityToDTO(final Route entity) {
        if (isNull(entity)) {
            return null;
        }

        return RouteDTO.builder()
                .routeId(entity.getRouteId())
                .routeName(entity.getRouteName())
                .routeNickName(entity.getRouteNickName())
                .createdOn(entity.getCreatedOn())
                .updatedOn(entity.getUpdatedOn())
                .build();
    }
}
