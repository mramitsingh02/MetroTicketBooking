package com.online.metro.registration.controller;

import com.online.metro.registration.dto.RouteDTO;
import com.online.metro.registration.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("route")
public class RouteController {

    private final RouteService routeService;

    @Autowired
    public RouteController(final RouteService routeService) {this.routeService = routeService;}

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


    @PostMapping("/")
    public ResponseEntity<RouteDTO> save(@RequestBody RouteDTO routeDTO) {

        if (nonNull(routeService.findByRouteName(routeDTO.getRouteName()))) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(routeService.saveRoute(routeDTO));
    }


}
