package com.online.metro.repository;

import com.online.metro.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookTicketRepository extends JpaRepository<Ticket, Long> {
}
