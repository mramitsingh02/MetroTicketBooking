package com.online.metro.registration.service;


import com.online.metro.registration.dto.StationDTO;
import com.online.metro.registration.dto.TripDTO;

import java.util.List;

public interface StationService {
    List<StationDTO> getAllStationByRouteName(String routeName);

    TripDTO getStationByRoute(String source, String destination);

    StationDTO saveStation(StationDTO stationDTO);
}
