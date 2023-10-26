package com.online.metro.service;


import com.online.metro.dto.StationDTO;
import com.online.metro.dto.StationDistanceDTO;

import java.util.List;

public interface StationService {
    List<StationDTO> getAllStationByRouteName(String routeName);

    StationDistanceDTO findDistanceBetweenStations(String source, String destination);
    StationDistanceDTO findDistanceBetweenStations(Long sourceId, Long destinationId);

    StationDTO saveStation(StationDTO stationDTO);
}
