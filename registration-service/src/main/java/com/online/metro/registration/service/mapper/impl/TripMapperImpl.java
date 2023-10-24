package com.online.metro.registration.service.mapper.impl;

import com.online.metro.registration.dto.PassengerType;
import com.online.metro.registration.dto.TripDTO;
import com.online.metro.registration.entity.Anonymous;
import com.online.metro.registration.entity.Passenger;
import com.online.metro.registration.entity.Staff;
import com.online.metro.registration.entity.Trip;
import com.online.metro.registration.service.mapper.TripMapper;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;


@Component
public class TripMapperImpl implements TripMapper {
    private static PassengerType getPassenger(final Passenger passenger) {
        if (passenger instanceof Anonymous)
            return PassengerType.ANONYMOUS;
        else if (passenger instanceof Staff) {
            return PassengerType.STAFF;
        }
        return PassengerType.ANONYMOUS;
    }

    @Override
    public Trip dtoToEntity(final TripDTO dto) {
        return Trip.builder()
                .tripId(dto.getTripId())
                .ticketId(dto.getTicketId())
                .tripType(dto.getTripType())
                .passenger(getPassenger(dto))
                .startOn(dto.getStartOn())
                .closeOn(dto.getCloseOn())
                .price(dto.getPrice())
                .build();
    }

    private static Passenger getPassenger(final TripDTO dto) {
        switch (dto.getPassenger()) {
            case STAFF -> {
                return new Staff(123l);
            }
            case ANONYMOUS -> {
                return new Anonymous();
            }
        }

        return Anonymous.builder().build();
    }

    @Override
    public TripDTO entityToDTO(final Trip entity) {
        if (isNull(entity)) {
            return null;
        }

        return TripDTO.builder()
                .tripId(entity.getTripId())
                .ticketId(entity.getTicketId())
                .tripType(entity.getTripType())
                .passenger(getPassenger(entity.getPassenger()))
                .startOn(entity.getStartOn())
                .closeOn(entity.getCloseOn())
                .price(entity.getPrice())
                .build();
    }
}
