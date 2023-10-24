package com.online.metro.registration.repository;

import com.online.metro.registration.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookTicketRepository extends JpaRepository<Ticket, Long> {
}
