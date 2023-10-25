package com.online.metro.registration.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long stationId;
    private String stationName;
    private String stationNickName;
    private Long previousStationId;
    private Long nextStationId;
    private float distance;
    private Long routeId;

    public static Station of() {
        return null;
    }
}
