package io.upschool.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Entity
@Table(name="flight")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "movement_city ", nullable = false, length = 150)
    private String cityOfMovement;
    @Column(name = "destination_city ", nullable = false, length = 150)
    private String destinationCity;
    @JsonFormat(shape=JsonFormat.Shape.STRING,  pattern="yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "flight_date ")
    private Date flightDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "airplane_Id", nullable = false)
    private Airplane airplane;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "airway_Id", nullable = false)
    private Airway airway;

}
