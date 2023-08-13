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
public class AirportSaveRequest {

    private long id;
    @Size(min = 2, max = 100, message = "İsim alanı minimum 2 maksimum 100 karater olabilir.")
    private String airportname;
    @NotBlank
    private Long cityId;

}
