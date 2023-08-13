package io.upschool.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketSaveResponse {
    private String ticketCode;
    private String customerName;
    private String customerSurname;
    private BigDecimal price;
    private String creditCardNo;
    private String movementCity;
    private String destinationCity;
    @JsonFormat(shape=JsonFormat.Shape.STRING,  pattern="yyyy-MM-dd HH:mm:ss")
    private Date flightDate;
    private boolean cancelled;


}
