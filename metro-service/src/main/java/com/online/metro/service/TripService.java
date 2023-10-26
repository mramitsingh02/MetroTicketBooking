package com.online.metro.service;

import com.online.metro.dto.TripDTO;

public interface TripService {
    TripDTO saveTrip(TripDTO dto);

    TripDTO findTripByTicketId(Long ticketId);
}
