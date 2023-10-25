package com.online.metro.registration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class TicketDTO {
    private Long ticketId;
    @NotNull(message = "Booking Station can't be null/empty.")
    private Long bookingStationId;
    @NotNull(message = "PassengerType can't be null/empty. eg. ANONYMOUS, STAFF")
    private PassengerType passengerType;
    @NotNull(message = "Source Station can't be null/empty.")
    private Long sourceStationId;
    @NotNull(message = "Destination Station can't be null/empty.")
    private Long destinationStationId;
    private LocalDateTime generateOn;
    private Double price;
    private PaymentType paymentType;
    private TripType tripType;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
