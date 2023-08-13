package io.upschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="airplane")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "airplan_name ", nullable = false, length = 150)
    private String airplaneName;
    @Column(name = "capacity ")
    private Integer capacity;

}
