package com.online.metro.service;

import com.online.metro.dto.PassengerType;
import com.online.metro.dto.PriceDTO;

public interface FairCalculateService {
    PriceDTO getFair(final PassengerType passengerType, final Long sourceStationId,
                     final Long destinationStationId);

}
