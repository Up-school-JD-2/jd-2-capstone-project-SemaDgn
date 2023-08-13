package io.upschool.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name="ticket")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(name = "ticket_code ")
    private String ticketCode;
    @Column(name = "price ",columnDefinition="Decimal(10,2)")
    private BigDecimal price;
    @Column(name = "creditcardno ", nullable = false)
    private String creditCardNo;
    @Column(name = "is_canceled")
    @Builder.Default()
    private Boolean canceled=Boolean.FALSE;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;
}
