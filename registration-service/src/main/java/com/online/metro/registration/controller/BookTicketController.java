package com.online.metro.registration.controller;

import com.online.metro.registration.dto.PaymentType;
import com.online.metro.registration.dto.TicketDTO;
import com.online.metro.registration.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("ticket")
public class BookTicketController {

    private final BookingService bookingService;

    @Autowired
    public BookTicketController(final BookingService bookingService) {this.bookingService = bookingService;}


    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketDTO> findByTicketId(@PathVariable Long ticketId)
    {
        if (nonNull(ticketId)) {
            return ResponseEntity.ok(bookingService.findByTicketId(ticketId));
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/")
    public ResponseEntity<TicketDTO> saveTicket(@RequestBody TicketDTO dto) {
        return ResponseEntity.ok(bookingService.saveTicket(dto));
    }

    @PostMapping("/")
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
