package com.online.metro.service.mapper.impl;

import com.online.metro.dto.TicketDTO;
import com.online.metro.entity.Ticket;
import com.online.metro.service.mapper.TicketMapper;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;


@Component
public class TicketMapperImpl implements TicketMapper {
    @Override
    public Ticket dtoToEntity(final TicketDTO dto) {
        return Ticket.builder()
                .ticketId(dto.getTicketId())
                .sourceStationId(dto.getSourceStationId())
                .destinationStationId(dto.getDestinationStationId())
                .fear(dto.getPrice())
                .generatedOn(dto.getGenerateOn())
                .bookingStationId(dto.getBookingStationId())
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
                .sourceStationId(entity.getSourceStationId())
                .destinationStationId(entity.getDestinationStationId())
                .price(entity.getFear())
                .generateOn(entity.getGeneratedOn())
                .bookingStationId(entity.getBookingStationId())
                .passengerType(entity.getPassengerType())
                .paymentType(entity.getPaymentType())
                .createdOn(entity.getCreatedOn())
                .updatedOn(entity.getUpdatedOn())
                .build();
    }
}
