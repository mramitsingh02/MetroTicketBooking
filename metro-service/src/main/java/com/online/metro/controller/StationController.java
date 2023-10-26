package com.online.metro.controller;

import com.online.metro.dto.StationDTO;
import com.online.metro.dto.StationDistanceDTO;
import com.online.metro.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("station")
public class StationController {
    private final StationService stationService;

    @Autowired
    public StationController(final StationService stationService) {this.stationService = stationService;}

    @GetMapping("/route/{routeName}")
    public ResponseEntity<List<StationDTO>> listOfStation(@PathVariable String routeName) {
        return ResponseEntity.ok(stationService.getAllStationByRouteName(routeName));
    }
    @GetMapping("/source/{source}/destination/{destination}")
    public ResponseEntity<StationDistanceDTO> listOfStation(@PathVariable String source, @PathVariable String destination) {
        return ResponseEntity.ok(stationService.findDistanceBetweenStations(source, destination));
    }
    @PostMapping("/route/{routeName}")
    public ResponseEntity<StationDTO> save(@RequestBody StationDTO stationDTO) {
        return ResponseEntity.ok(stationService.saveStation(stationDTO));
    }

}
