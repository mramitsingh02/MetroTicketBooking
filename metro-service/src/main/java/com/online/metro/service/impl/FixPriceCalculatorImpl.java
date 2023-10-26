package com.online.metro.service.impl;

import com.online.metro.dto.StationDistanceDTO;
import com.online.metro.service.PriceCalculator;
import com.online.metro.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("fixPriceCalculator")
public class FixPriceCalculatorImpl implements PriceCalculator {


    private final StationService stationService;

    @Autowired
    public FixPriceCalculatorImpl(final StationService stationService) {this.stationService = stationService;}

    @Override
    public double calculatePrice(final Long from, final Long to) {
        final StationDistanceDTO distanceBetweenStations = stationService.findDistanceBetweenStations(from, to);
        return distanceBetweenStations.getNumberOfStop() * 9;
    }
}
