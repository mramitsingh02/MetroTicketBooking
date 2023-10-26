package com.online.metro.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class RouteDTO {
    private Long routeId;
    private String routeName;
    private String routeNickName;
    private List<StationDTO> stations;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

}
