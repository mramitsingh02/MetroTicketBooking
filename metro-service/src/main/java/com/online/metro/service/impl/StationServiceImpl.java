package com.online.metro.service.impl;

import com.online.metro.dto.StationDTO;
import com.online.metro.dto.StationDistanceDTO;
import com.online.metro.entity.Route;
import com.online.metro.entity.Station;
import com.online.metro.repository.RouteRepository;
import com.online.metro.repository.StationRepository;
import com.online.metro.service.StationService;
import com.online.metro.service.mapper.StationMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;
    private final RouteRepository routeRepository;
    private final StationMapper stationMapper;

    public StationServiceImpl(final StationRepository stationRepository, final RouteRepository routeRepository, final StationMapper stationMapper) {
        this.stationRepository = stationRepository;
        this.routeRepository = routeRepository;
        this.stationMapper = stationMapper;
    }

    @Override
    public List<StationDTO> getAllStationByRouteName(final String routeName) {
        final Route byRouteName = routeRepository.findByRouteName(routeName);

        return stationMapper.entitiesToDTOs(stationRepository.findByRouteId(byRouteName.getRouteId()));
    }

    @Override
    public StationDistanceDTO findDistanceBetweenStations(final String source, final String destination) {
        final Station fromStation = stationRepository.findByStationName(source).orElseThrow(() -> new RuntimeException(
                "Source Station not found."));
        final Station toStation = stationRepository.findByStationName(destination).orElseThrow(() -> new RuntimeException(
                "Destination Station not found."));;
        Station currentStation = fromStation;
        AtomicInteger numberOfStation = new AtomicInteger(0);
        AtomicInteger totalDistance = new AtomicInteger(0);

        if (fromStation.getStationId() < toStation.getStationId()) {
            moveForward(toStation, currentStation, numberOfStation, totalDistance);
        } else if (fromStation.getStationId() > toStation.getStationId()) {
            moveBackward(toStation, currentStation, numberOfStation, totalDistance);
        }

        return StationDistanceDTO.builder()
                .numberOfStop(numberOfStation.get())
                .distance(totalDistance.get())
                .source(stationMapper.entityToDTO(fromStation))
                .destination(stationMapper.entityToDTO(toStation))
                .build();
    }

    @Override
    public StationDistanceDTO findDistanceBetweenStations(final Long sourceId, final Long destinationId) {
        final Station fromStation = stationRepository.findById(sourceId).orElseThrow(() -> new RuntimeException(
                "Source Station not found."));
        final Station toStation = stationRepository.findById(destinationId).orElseThrow(() -> new RuntimeException(
                "Destination Station not found."));
        Station currentStation = fromStation;
        AtomicInteger numberOfStation = new AtomicInteger(0);
        AtomicInteger totalDistance = new AtomicInteger(0);

        if (fromStation.getStationId() < toStation.getStationId()) {
            moveForward(toStation, currentStation, numberOfStation, totalDistance);
        } else if (fromStation.getStationId() > toStation.getStationId()) {
            moveBackward(toStation, currentStation, numberOfStation, totalDistance);
        }
        return StationDistanceDTO.builder()
                .numberOfStop(numberOfStation.get())
                .distance(totalDistance.get())
                .source(stationMapper.entityToDTO(fromStation))
                .destination(stationMapper.entityToDTO(toStation))
                .build();
    }

    private void moveForward(final Station toStation, Station currentStation, final AtomicInteger numberOfStation, final AtomicInteger totalDistance) {
        while (Objects.nonNull(currentStation) && Objects.nonNull(currentStation.getNextStationId())) {
            if (currentStation.getStationName().equals(toStation.getStationName())) {
                break;
            }
            totalDistance.addAndGet((int) currentStation.getDistance());
            numberOfStation.incrementAndGet();
            currentStation = stationRepository.findById(currentStation.getNextStationId()).orElse(Station.of());
        }
    }

    private void moveBackward(final Station toStation, Station currentStation, final AtomicInteger numberOfStation, final AtomicInteger totalDistance) {
        while (Objects.nonNull(currentStation) && Objects.nonNull(currentStation.getNextStationId())) {
            if (currentStation.getStationName().equals(toStation.getStationName())) {
                break;
            }
            totalDistance.addAndGet((int) currentStation.getDistance());
            numberOfStation.incrementAndGet();
            currentStation = stationRepository.findById(currentStation.getPreviousStationId()).orElse(Station.of());
        }
    }

    @Override
    public StationDTO saveStation(final StationDTO stationDTO) {
        return stationMapper.entityToDTO(stationRepository.save(stationMapper.dtoToEntity(stationDTO)));
    }
}
