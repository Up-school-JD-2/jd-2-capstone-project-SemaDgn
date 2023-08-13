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
public class AirplaneSaveRequest {
    @NotBlank
    private Long id;
    @NotBlank
    @Size(min = 2, max = 100)
    private String airplaneName;
    @NotBlank
    private Integer capacity;
}
