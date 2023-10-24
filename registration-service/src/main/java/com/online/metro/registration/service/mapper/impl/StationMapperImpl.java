package com.online.metro.registration.service.mapper.impl;

import com.online.metro.registration.dto.StationDTO;
import com.online.metro.registration.entity.Station;
import com.online.metro.registration.service.mapper.StationMapper;
import com.online.metro.registration.service.mapper.StationMapper;
import org.springframework.stereotype.Component;

@Component
public class StationMapperImpl implements StationMapper {
    @Override
    public Station dtoToEntity(final StationDTO dto) {
        return new Station(dto.getStationId(), dto.getStationName(), dto.getStationNickName(),
                dto.getPreviousStationId().getStationId(), dto.getNextStationId().getStationId(),dto.getDistance(), dto.getRouteId());
    }

    @Override
    public StationDTO entityToDTO(final Station entity) {
        return StationDTO.builder()
                .routeId(entity.getStationId())
                .stationName(entity.getStationName())
                .stationNickName(entity.getStationNickName())
                .nextStationId(StationDTO.of(entity.getNextStationId()))
                .previousStationId(StationDTO.of(entity.getPreviousStationId()))
                .distance(entity.getDistance())
                .routeId(entity.getRouteId())
                .build();
    }
}
