package com.online.metro.registration.service;


import com.online.metro.registration.dto.StationDTO;
import com.online.metro.registration.dto.StationDistanceDTO;

import java.util.List;

public interface StationService {
    List<StationDTO> getAllStationByRouteName(String routeName);

    StationDistanceDTO findDistanceBetweenStations(String source, String destination);
    StationDistanceDTO findDistanceBetweenStations(Long sourceId, Long destinationId);

    StationDTO saveStation(StationDTO stationDTO);
}
