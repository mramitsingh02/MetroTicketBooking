package com.online.metro.registration.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class StationDTO {
    private Long stationId;
    private String stationName;
    private String stationNickName;
    private StationDTO previousStationId;
    private StationDTO nextStationId;
    private float distance;
    private Long routeId;

    public static StationDTO of(final Long nextStationId) {
        return StationDTO.builder()
                .stationId(nextStationId).build();
    }
}
