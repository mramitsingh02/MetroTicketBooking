package com.online.metro.service;

import com.online.metro.dto.TicketDTO;

public interface BookingService {
    TicketDTO saveTicket(TicketDTO dto);

    TicketDTO findByTicketId(Long ticketId);
}
