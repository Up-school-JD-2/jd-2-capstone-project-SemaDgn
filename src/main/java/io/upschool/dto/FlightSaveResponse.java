package io.upschool.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightSaveResponse {
    private Long id;
    private String movementCity;
    private String destinationCity;
    @JsonFormat(shape=JsonFormat.Shape.STRING,  pattern="yyyy-MM-dd HH:mm:ss")
    private Date flightDate;
     private String airplaneName;
    private String airwayName;


}
