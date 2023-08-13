package io.upschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name="customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "identity_no ", nullable = false, length = 11)
    private Long identityNo;
    @Column(name = "name ", nullable = false, length = 150)
    private String name;
    @Column(name = "surname ", nullable = false, length = 150)
    private String surname;
    @Column(name = "Phone ")
    private String phone;
    @Column(name = "balance " ,columnDefinition="Decimal(10,2)" )
    @Builder.Default()
    private BigDecimal balance=BigDecimal.ZERO;


}
