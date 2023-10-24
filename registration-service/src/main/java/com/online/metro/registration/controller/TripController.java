package com.online.metro.registration.controller;

import com.online.metro.registration.dto.TicketDTO;
import com.online.metro.registration.dto.TripDTO;
import com.online.metro.registration.dto.TripType;
import com.online.metro.registration.service.BookingService;
import com.online.metro.registration.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("trip")
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
        if (nonNull(ticketDTO)) {
            return ResponseEntity.ok(new TripDTO(ticketDTO).start());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{ticketId}/stop")
    public ResponseEntity<TripDTO> stopTrip(@PathVariable Long ticketId)
    {
        final TicketDTO ticketDTO = bookingService.findByTicketId(ticketId);
        if (nonNull(ticketDTO)) {
            return ResponseEntity.ok(new TripDTO(ticketDTO).stop());
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
        return ResponseEntity.ok(tripService.saveTrip(dto));
    }


}
