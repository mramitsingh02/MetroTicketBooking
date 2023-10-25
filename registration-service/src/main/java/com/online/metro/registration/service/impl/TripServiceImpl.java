package com.online.metro.registration.service.impl;

import com.online.metro.registration.dto.TripDTO;
import com.online.metro.registration.repository.TripRepository;
import com.online.metro.registration.service.TripService;
import com.online.metro.registration.service.mapper.TripMapper;
import org.springframework.stereotype.Service;

@Service
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;
    private final TripMapper tripMapper;

    public TripServiceImpl(final TripRepository tripRepository, final TripMapper tripMapper) {
        this.tripRepository = tripRepository;
        this.tripMapper = tripMapper;
    }

    @Override
    public TripDTO saveTrip(final TripDTO dto) {
        return tripMapper.entityToDTO(tripRepository.save(tripMapper.dtoToEntity(dto)));
    }

    @Override
    public TripDTO findTripByTicketId(final Long ticketId) {
        return tripMapper.entityToDTO(tripRepository.findByTicketId(ticketId));
    }
}
