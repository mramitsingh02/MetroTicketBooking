package com.online.metro.registration.service;

import com.online.metro.registration.dto.PassengerType;
import com.online.metro.registration.dto.PriceDTO;

public interface FairCalculateService {
    PriceDTO getFair(final PassengerType passengerType, final Long sourceStationId,
                     final Long destinationStationId);

}
