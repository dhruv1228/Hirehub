package com.hirehub.company.dto;

import com.hirehub.company.enums.CompanySize;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResponse {

    private Long id;

    private String name;

    private String description;

    private String website;

    private String industry;

    private CompanySize companySize;

    private String headquarters;

    private String logoUrl;
}