package com.online.metro.registration.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static java.util.Objects.isNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public final class StationDTO {
    private Long stationId;
    private String stationName;
    private String stationNickName;
    private StationDTO previousStationId;
    private StationDTO nextStationId;
    private float distance;
    private Long routeId;

    public static StationDTO of(final Long stationId) {
        if(isNull(stationId)){
            return null;
        }

        return StationDTO.builder()
                .stationId(stationId).build();
    }
}
