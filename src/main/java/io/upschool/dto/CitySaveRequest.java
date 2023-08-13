package io.upschool.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CitySaveRequest {
    private long id;
    @NotBlank
    @Size(min = 2, max = 100,message = "İsim alanı minimum 2 maksimum 100 karater olabilir.")
    @NotBlank
    private String name;


 }
