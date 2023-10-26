package com.online.metro.registration.controller;

import com.online.metro.registration.dto.TicketDTO;
import com.online.metro.registration.dto.TripDTO;
import com.online.metro.registration.dto.TripType;
import com.online.metro.registration.service.BookingService;
import com.online.metro.registration.service.TripService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("trip")
@Slf4j
public class TripController {

    private final TripService tripService;
    private final BookingService bookingService;

    @Autowired
    public TripController(final TripService tripService, final BookingService bookingService) {
        this.tripService = tripService;
        this.bookingService = bookingService;
    }


    @PutMapping("/{ticketId}/start")
    public ResponseEntity<TripDTO> startTrip(@PathVariable Long ticketId)
    {
        final TicketDTO ticketDTO = bookingService.findByTicketId(ticketId);
        final TripDTO tripByTicketId = tripService.findTripByTicketId(ticketId);
        if (nonNull(tripByTicketId) && TripType.START == tripByTicketId.getTripType()) {
            log.error("Trip is already stared.");
            return ResponseEntity.ok(tripByTicketId);
        }
        if (nonNull(ticketDTO)) {
            final TripDTO startedTrip = new TripDTO(ticketDTO).start();
            final TripDTO persistenceTrip = tripService.saveTrip(startedTrip);
            return ResponseEntity.ok(persistenceTrip);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{ticketId}/stop")
    public ResponseEntity<TripDTO> stopTrip(@PathVariable Long ticketId)
    {
        final TicketDTO ticketDTO = bookingService.findByTicketId(ticketId);
        final TripDTO tripByTicketId = tripService.findTripByTicketId(ticketId);
        if (nonNull(tripByTicketId) && TripType.STOP == tripByTicketId.getTripType()) {
            log.error("Trip is already stop.");
            return ResponseEntity.ok(tripByTicketId);
        }
        if (nonNull(ticketDTO)) {
            final TripDTO persistenceTrip = tripService.saveTrip(tripByTicketId.stop());
            return ResponseEntity.ok(persistenceTrip);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketDTO> findByTicketId(@PathVariable Long ticketId)
    {
        if (nonNull(ticketId)) {
            return ResponseEntity.ok(bookingService.findByTicketId(ticketId));
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/")
    public ResponseEntity<TripDTO> saveTrip(@RequestBody TripDTO dto) {
        dto.setTripType(TripType.NOT_TO_START);
        final TripDTO tripDTO = tripService.saveTrip(dto);
        return  ResponseEntity.created(URI.create("/" + tripDTO.getTicketId())).body(tripDTO);
    }


}
