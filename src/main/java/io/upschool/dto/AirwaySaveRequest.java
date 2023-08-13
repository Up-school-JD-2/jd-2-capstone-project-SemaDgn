package io.upschool.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirwaySaveRequest {
    private Long id;
    @NotBlank
    @Size(min = 2, max = 100)
    private String airwayName;
    @NotBlank
    private Long airportId;
}
