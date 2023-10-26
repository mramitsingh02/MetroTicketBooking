package com.online.metro.service;


import com.online.metro.dto.RouteDTO;

import java.util.List;

public interface RouteService {
    List<RouteDTO> getAllRoutes();

    RouteDTO findByRouteName(String routeName);

    RouteDTO saveRoute(RouteDTO routeDTO);

    RouteDTO findById(Long routeId);
}
