package com.online.metro.registration.service.impl;

import com.online.metro.registration.dto.PassengerType;
import com.online.metro.registration.dto.PriceDTO;
import com.online.metro.registration.dto.StationDistanceDTO;
import com.online.metro.registration.service.FairCalculateService;
import com.online.metro.registration.service.PriceCalculator;
import com.online.metro.registration.service.StationService;
import org.springframework.stereotype.Service;

@Service
public class FairCalculateServiceImpl implements FairCalculateService {

    private final PriceCalculator noPriceCalculator;
    private final PriceCalculator fixPriceCalculator;
    private final PriceCalculator servicePriceCalculator;
    private final StationService stationService;
    public FairCalculateServiceImpl(final PriceCalculator noPriceCalculator, final PriceCalculator fixPriceCalculator, final PriceCalculator servicePriceCalculator, final StationService stationService) {
        this.noPriceCalculator = noPriceCalculator;
        this.fixPriceCalculator = fixPriceCalculator;
        this.servicePriceCalculator = servicePriceCalculator;
        this.stationService = stationService;
    }

    @Override
    public PriceDTO getFair(final PassengerType passengerType, final Long sourceStationId,
                            final Long destinationStationId)
    {
        Double price = 0d;
        final StationDistanceDTO distanceBetweenStations = stationService.findDistanceBetweenStations(sourceStationId, destinationStationId);
        if (PassengerType.ANONYMOUS == passengerType) {
            price = servicePriceCalculator.calculatePrice(sourceStationId, destinationStationId);
            price += fixPriceCalculator.calculatePrice(sourceStationId, destinationStationId);
        } else if (PassengerType.STAFF == passengerType) {
            price = noPriceCalculator.calculatePrice(sourceStationId, destinationStationId);
        }
        return PriceDTO.builder()
                .fair(price)
                .source(distanceBetweenStations.getSource())
                .destination(distanceBetweenStations.getDestination())
                .distance(distanceBetweenStations.getDistance())
                .build();
    }
}
