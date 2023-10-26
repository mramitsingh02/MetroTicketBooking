package com.online.metro.service.impl;

import com.online.metro.dto.RouteDTO;
import com.online.metro.repository.RouteRepository;
import com.online.metro.service.RouteService;
import com.online.metro.service.mapper.RouteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository repository;
    private final RouteMapper routeMapper;

    @Autowired
    public RouteServiceImpl(final RouteRepository repository, final RouteMapper routeMapper) {
        this.repository = repository;
        this.routeMapper = routeMapper;
    }


    @Override
    public List<RouteDTO> getAllRoutes() {
        return routeMapper.entitiesToDTOs(repository.findAll());
    }

    @Override
    public RouteDTO findByRouteName(final String routeName) {
        return routeMapper.entityToDTO(repository.findByRouteName(routeName));
    }

    @Override
    public RouteDTO saveRoute(final RouteDTO routeDTO) {
        if (isNull(routeDTO)) {
            return null;
        }

        return routeMapper.entityToDTO(repository.save(routeMapper.dtoToEntity(routeDTO)));
    }

    @Override
    public RouteDTO findById(final Long routeId) {
        return routeMapper.entityToDTO(repository.findById(routeId).orElse(null));
    }
}
