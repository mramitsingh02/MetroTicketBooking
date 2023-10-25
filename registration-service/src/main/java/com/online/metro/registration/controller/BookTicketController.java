package com.online.metro.registration.controller;

import com.online.metro.registration.dto.PaymentType;
import com.online.metro.registration.dto.PriceDTO;
import com.online.metro.registration.dto.TicketDTO;
import com.online.metro.registration.service.BookingService;
import com.online.metro.registration.service.FairCalculateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.LocalDateTime;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("ticket")
public class BookTicketController {

    private final BookingService bookingService;
    private final FairCalculateService fairCalculateService;

    @Autowired
    public BookTicketController(final BookingService bookingService, final FairCalculateService fairCalculateService) {
        this.bookingService = bookingService;
        this.fairCalculateService = fairCalculateService;
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
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<TicketDTO> bookTicket(@Valid @RequestBody TicketDTO dto) {

        final PriceDTO fair = fairCalculateService.getFair(dto.getPassengerType(), dto.getSourceStationId(),
                dto.getDestinationStationId());
        dto.setPrice(fair.getFair());
        return ResponseEntity.created(URI.create("/" + dto.getTicketId())).body(bookingService.saveTicket(dto));
    }

    @PostMapping("/{ticketId}")
    public ResponseEntity<TicketDTO> cancelTicket(@PathVariable Long ticketId) {
        if (nonNull(ticketId)) {
            final TicketDTO ticketDTO = bookingService.findByTicketId(ticketId);
            ticketDTO.setPaymentType(PaymentType.CASH_REFUND);
            ticketDTO.setUpdatedOn(LocalDateTime.now());
            return ResponseEntity.ok(ticketDTO);
        }
        return ResponseEntity.notFound().build();
    }


}
