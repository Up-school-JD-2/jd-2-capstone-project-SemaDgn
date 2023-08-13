package io.upschool.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerSaveRequest {
    @NotBlank
    private Long identityNo;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String phone;

}
