package com.online.metro.repository;

import com.online.metro.dto.PassengerType;
import com.online.metro.dto.PaymentType;
import com.online.metro.entity.Anonymous;
import com.online.metro.entity.Staff;
import com.online.metro.entity.Ticket;
import com.online.metro.entity.Trip;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TripRepositoryTest {
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldSaveTripForAnonymousUser() {
        final Ticket ticket = Ticket.builder()
                .sourceStationId(1001l)
                .destinationStationId(1010l)
                .fear(40d)
                .generatedOn(LocalDateTime.now())
                .bookingStationId(1001l)
                .passengerType(PassengerType.ANONYMOUS)
                .paymentType(PaymentType.CASH)
                .createdOn(LocalDateTime.now())
                .build();

        final Ticket ticketSaved = ticketRepository.save(ticket);

        Trip trip = Trip.builder()
                .price(45d)
                .ticketId(ticketSaved.getTicketId())
                .startOn(LocalDateTime.now())
                .passenger(new Anonymous())
                .build();
        final Trip saved = tripRepository.save(trip);

        Optional<Trip> opt01 = tripRepository.findById(saved.getTripId());
        assertTrue(opt01.isPresent());
        assertEquals(opt01.get().getTicketId(), ticketSaved.getTicketId());

    }

    @Test
    void shouldSaveTripForStaff() {
        final Ticket ticket = Ticket.builder()
                .sourceStationId(1001l)
                .destinationStationId(1010l)
                .fear(40d)
                .generatedOn(LocalDateTime.now())
                .bookingStationId(1001l)
                .passengerType(PassengerType.ANONYMOUS)
                .paymentType(PaymentType.CASH)
                .createdOn(LocalDateTime.now())
                .build();
        final Ticket ticketSaved = ticketRepository.save(ticket);
        Trip trip = Trip.builder()
                .price(45d)
                .ticketId(ticketSaved.getTicketId())
                .startOn(LocalDateTime.now())
                .passenger(new Staff(123l))
                .build();
        final Trip saved = tripRepository.save(trip);

        Optional<Trip> opt01 = tripRepository.findById(saved.getTripId());
        assertTrue(opt01.isPresent());
        assertEquals(opt01.get().getTicketId(), ticketSaved.getTicketId());

    }
}