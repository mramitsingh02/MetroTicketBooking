package com.online.metro.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.online.metro.entity.Passenger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public final class PriceDTO {
    private Passenger passenger;
    private StationDTO source;
    private StationDTO destination;
    private float distance;
    private Double fair;

}
