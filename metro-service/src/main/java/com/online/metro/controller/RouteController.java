package com.online.metro.controller;

import com.online.metro.dto.RouteDTO;
import com.online.metro.dto.StationDTO;
import com.online.metro.service.RouteService;
import com.online.metro.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@RestController
@RequestMapping("route")
public class RouteController {

    private final RouteService routeService;
    private final StationService stationService;

    @Autowired
    public RouteController(final RouteService routeService, final StationService stationService) {
        this.routeService = routeService;
        this.stationService = stationService;
    }

    @GetMapping("/routes")
    public ResponseEntity<List<RouteDTO>> listOfRoutes() {
        return ResponseEntity.ok(routeService.getAllRoutes());

    }

    @GetMapping("/")
    public ResponseEntity<RouteDTO> getRouteByRequestParam(@RequestParam(required = false, name = "name") String routeName,
                                                           @RequestParam(required = false, name = "color") String routeColor)
    {

        if (nonNull(routeName)) {
            return ResponseEntity.ok(routeService.findByRouteName(routeName));
        }

        return ResponseEntity.badRequest().build();

    }

    @PostMapping("/{routeId}")
    public ResponseEntity<StationDTO> saveStation(@RequestBody StationDTO stationDTO, @PathVariable Long routeId) {
        RouteDTO dto = routeService.findById(routeId);
        if (isNull(dto)) {
            return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(406),
                    routeId + " Route not found")).build();
        }
        stationDTO.setRouteId(routeId);
        return ResponseEntity.ok(stationService.saveStation(stationDTO));
    }
    @GetMapping("/{routeId}")
    public ResponseEntity<RouteDTO> getStationsByRouteId(@PathVariable Long routeId) {
        RouteDTO dto = routeService.findById(routeId);
        if (isNull(dto)) {
            return ResponseEntity.of(ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(406),
                    routeId + " Route not found")).build();
        }
        dto.setStations(stationService.getAllStationByRouteName(dto.getRouteName()));
        return ResponseEntity.ok(dto);
    }


    @PostMapping("/")
    public ResponseEntity<RouteDTO> save(@RequestBody RouteDTO routeDTO) {

        if (nonNull(routeService.findByRouteName(routeDTO.getRouteName()))) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(routeService.saveRoute(routeDTO));
    }


}
