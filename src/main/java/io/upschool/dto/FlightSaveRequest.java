package io.upschool.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightSaveRequest {
    private Long id;
    @NotBlank
    private String movementCity;
    @NotBlank
    private String destinationCity;
    @NotBlank
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date flightDate;
    @NotBlank
    private Long airwayId;
    @NotBlank
    private Long airplaneId;

}
