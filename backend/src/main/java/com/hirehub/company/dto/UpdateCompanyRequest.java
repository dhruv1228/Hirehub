package com.hirehub.company.dto;

import com.hirehub.company.enums.CompanySize;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCompanyRequest {

    private String description;

    private String website;

    private String industry;

    private CompanySize companySize;

    private String headquarters;

    private String logoUrl;
}