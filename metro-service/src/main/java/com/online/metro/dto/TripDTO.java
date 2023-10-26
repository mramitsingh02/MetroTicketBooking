package com.online.metro.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Clock;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class TripDTO {
    private Long tripId;
    private Long ticketId;
    private PassengerType passenger;
    private Float distanceCover;
    private Double price;
    private TripType tripType;
    private LocalDateTime startOn;
    private LocalDateTime closeOn;

    public TripDTO(final TicketDTO ticketDTO) {
        this(null, ticketDTO.getTicketId(), ticketDTO.getPassengerType(), 0f, ticketDTO.getPrice(), TripType.NOT_TO_START, null, null);
    }

    public TripDTO start() {
        tripType = TripType.START;
        startOn = LocalDateTime.now(Clock.systemDefaultZone());
        return this;
    }

    public TripDTO stop() {
        tripType = TripType.STOP;
        closeOn = LocalDateTime.now(Clock.systemDefaultZone());
        return this;
    }
}
