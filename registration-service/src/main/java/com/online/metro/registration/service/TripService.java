package com.online.metro.registration.service;

import com.online.metro.registration.dto.TripDTO;

public interface TripService {
    TripDTO saveTrip(TripDTO dto);

    TripDTO findTripByTicketId(Long ticketId);
}
