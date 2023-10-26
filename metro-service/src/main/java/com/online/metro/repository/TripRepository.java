package com.online.metro.repository;

import com.online.metro.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    Trip findByTicketId(Long ticketId);
}
