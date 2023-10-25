package com.online.metro.registration.repository;

import com.online.metro.registration.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {
    Optional<Station> findByStationName(String source);

    List<Station> findByRouteId(Long routeName);
}
