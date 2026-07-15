package com.hirehub.company.dto;

import com.hirehub.company.enums.CompanySize;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCompanyRequest {

    @NotBlank
    private String name;

    private String description;

    private String website;

    private String industry;

    private CompanySize companySize;

    private String headquarters;
}