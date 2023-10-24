package com.online.metro.registration.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDTO {
    private Long ticketId;
    private String bookingStation;
    private PassengerType passengerType;
    private String routeNickName;
    private String sourceStation;
    private String destinationStation;
    private LocalDateTime generateOn;
    private Double price;
    private PaymentType paymentType;
    private TripType tripType;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
