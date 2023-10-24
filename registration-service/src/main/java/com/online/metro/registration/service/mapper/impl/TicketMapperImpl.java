package com.online.metro.registration.service.mapper.impl;

import com.online.metro.registration.dto.TicketDTO;
import com.online.metro.registration.entity.Ticket;
import com.online.metro.registration.service.mapper.TicketMapper;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;


@Component
public class TicketMapperImpl implements TicketMapper {
    @Override
    public Ticket dtoToEntity(final TicketDTO dto) {
        return Ticket.builder()
                .ticketId(dto.getTicketId())
                .sourceStation(dto.getSourceStation())
                .destinationStation(dto.getDestinationStation())
                .fear(dto.getPrice())
                .generatedOn(dto.getGenerateOn())
                .bookingStation(dto.getBookingStation())
                .passengerType(dto.getPassengerType())
                .paymentType(dto.getPaymentType())
                .createdOn(dto.getCreatedOn())
                .updatedOn(dto.getUpdatedOn())
                .build();
    }

    @Override
    public TicketDTO entityToDTO(final Ticket entity) {
        if (isNull(entity)) {
            return null;
        }

        return TicketDTO.builder()
                .ticketId(entity.getTicketId())
                .sourceStation(entity.getSourceStation())
                .destinationStation(entity.getDestinationStation())
                .price(entity.getFear())
                .generateOn(entity.getGeneratedOn())
                .bookingStation(entity.getBookingStation())
                .passengerType(entity.getPassengerType())
                .paymentType(entity.getPaymentType())
                .createdOn(entity.getCreatedOn())
                .updatedOn(entity.getUpdatedOn())
                .build();
    }
}
