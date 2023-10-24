package com.online.metro.registration.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteDTO {
    private Long routeId;
    private String routeName;
    private String routeNickName;
    private List<StationDTO> stations;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

}
