package com.online.metro.registration.service.impl;

import com.online.metro.registration.dto.TicketDTO;
import com.online.metro.registration.repository.BookTicketRepository;
import com.online.metro.registration.service.BookingService;
import com.online.metro.registration.service.mapper.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookTicketRepository bookTicketRepository;
    private final TicketMapper ticketMapper;

    @Autowired
    public BookingServiceImpl(final BookTicketRepository bookingRepository, final TicketMapper ticketMapper) {
        this.bookTicketRepository = bookingRepository;
        this.ticketMapper = ticketMapper;
    }

    @Override
    public TicketDTO saveTicket(final TicketDTO dto) {
        return ticketMapper.entityToDTO(bookTicketRepository.save(ticketMapper.dtoToEntity(dto)));
    }

    @Override
    public TicketDTO findByTicketId(final Long ticketId) {
        return ticketMapper.entityToDTO(bookTicketRepository.findById(ticketId).orElse(null));
    }
}
