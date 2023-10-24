package com.online.metro.registration.entity;

import com.online.metro.registration.dto.PassengerType;
import com.online.metro.registration.dto.PaymentType;
import com.online.metro.registration.dto.TripType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate
@DynamicInsert
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ticketId;
    private String sourceStation;
    private String destinationStation;
    private Double fear;
    @CreationTimestamp
    private LocalDateTime generatedOn;
    private String bookingStation;
    private PassengerType passengerType;
    private PaymentType paymentType;
    private TripType tripType;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

}
