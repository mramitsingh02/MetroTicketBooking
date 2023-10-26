package com.online.metro.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public final class StationDistanceDTO {
    private StationDTO source;
    private StationDTO destination;
    private float distance;
    private int numberOfStop;

}
