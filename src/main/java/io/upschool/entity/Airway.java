package io.upschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="airway")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Airway {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name ", nullable = false, length = 150)
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "airpot_id", nullable = false)
    private Airport airpot;

}
