package io.upschool.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketSaveRequest {
    @NotBlank
    @Size(min = 0, max = 4)
    private String TicketCode;
    @NotBlank
    @Size(min = 0, max = 16)
    private String creditCardNo;
    @NotBlank
    private BigDecimal price;
    @NotBlank
    private Long customerId;
    @NotBlank
    private Long flightId;


}
