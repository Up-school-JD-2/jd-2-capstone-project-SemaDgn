package io.upschool.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerSaveResponse {
    private Long id;
    private Long identityNo;
    private String name;
    private String surname;
    private String phone;
    private BigDecimal balance;

}
