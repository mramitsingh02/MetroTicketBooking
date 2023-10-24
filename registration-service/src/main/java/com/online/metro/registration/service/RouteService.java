package com.online.metro.registration.service;


import com.online.metro.registration.dto.RouteDTO;

import java.util.List;

public interface RouteService {
    List<RouteDTO> getAllRoutes();

    RouteDTO findByRouteName(String routeName);

    RouteDTO saveRoute(RouteDTO routeDTO);
}
