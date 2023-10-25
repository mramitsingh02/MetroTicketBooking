package com.online.metro.registration.service.mapper.impl;

import com.online.metro.registration.dto.StationDTO;
import com.online.metro.registration.entity.Station;
import com.online.metro.registration.service.mapper.StationMapper;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class StationMapperImpl implements StationMapper {
    @Override
    public Station dtoToEntity(final StationDTO dto) {


        return Station.builder()
                .stationId(dto.getStationId())
                .routeId(dto.getStationId())
                .stationName(dto.getStationName())
                .stationNickName(dto.getStationNickName())
                .nextStationId(getStationId(dto.getNextStationId()))
                .previousStationId(getStationId(dto.getPreviousStationId()))
                .distance(dto.getDistance())
                .routeId(dto.getRouteId())
                .build();
    }

    private Long getStationId(final StationDTO stationDTO) {
        if(isNull(stationDTO))
        {
            return null;
        }
        return stationDTO.getStationId();
    }

    @Override
    public StationDTO entityToDTO(final Station entity) {
        if (isNull(entity)) {
            return null;
        }

        return StationDTO.builder()
                .stationId(entity.getStationId())
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
