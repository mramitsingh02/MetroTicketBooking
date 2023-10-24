package com.online.metro.registration.service;

import com.online.metro.registration.dto.TicketDTO;

public interface BookingService {
    TicketDTO saveTicket(TicketDTO dto);

    TicketDTO findByTicketId(Long ticketId);
}
