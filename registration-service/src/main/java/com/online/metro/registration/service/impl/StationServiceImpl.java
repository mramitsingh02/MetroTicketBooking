package com.online.metro.registration.service.impl;

import com.online.metro.registration.dto.StationDTO;
import com.online.metro.registration.dto.TripDTO;
import com.online.metro.registration.entity.Route;
import com.online.metro.registration.entity.Station;
import com.online.metro.registration.repository.RouteRepository;
import com.online.metro.registration.repository.StationRepository;
import com.online.metro.registration.service.StationService;
import com.online.metro.registration.service.mapper.StationMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public TripDTO getStationByRoute(final String source, final String destination) {
        final Station fromStation = stationRepository.findByStationName(source);
        final Station toStation = stationRepository.findByStationName(destination);
        List<Station> stations = new ArrayList<>();
        Station currentStation = fromStation;
       /* while (Objects.nonNull(currentStation) && Objects.nonNull(currentStation.getNextStationId())) {
            stations.add(currentStation);
            if (currentStation.getStationName().equals(toStation.getStationName())) {
                break;
            }
            currentStation = stationRepository.findById(currentStation.getNextStationId()).orElse(null);
        }
*/

        return TripDTO.builder()


                .build();
    }

    @Override
    public StationDTO saveStation(final StationDTO stationDTO) {
        return stationMapper.entityToDTO(stationRepository.save(stationMapper.dtoToEntity(stationDTO)));
    }
}
