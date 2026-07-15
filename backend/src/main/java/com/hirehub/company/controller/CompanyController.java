package com.hirehub.company.controller;

import com.hirehub.common.response.ApiResponse;
import com.hirehub.company.dto.CompanyResponse;
import com.hirehub.company.dto.CreateCompanyRequest;
import com.hirehub.company.dto.UpdateCompanyRequest;
import com.hirehub.company.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('RECRUITER')")
    public ApiResponse<String> deleteCompany(@PathVariable Long id) {

        companyService.deleteCompany(id);

        return ApiResponse.success(
                "Company deleted successfully",
                "Deleted"
        );
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('RECRUITER')")
    public ApiResponse<CompanyResponse> updateCompany(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCompanyRequest request) {

        return ApiResponse.success(
                "Company updated successfully",
                companyService.updateCompany(id, request)
        );
    }

    @PostMapping
    @PreAuthorize("hasRole('RECRUITER')")
    public ApiResponse<CompanyResponse> createCompany(
            @Valid @RequestBody CreateCompanyRequest request) {

        return ApiResponse.success(
                "Company created successfully",
                companyService.createCompany(request)
        );
    }

    @GetMapping("/my-company")
@PreAuthorize("hasRole('RECRUITER')")
public ApiResponse<CompanyResponse> getMyCompany() {

    return ApiResponse.<CompanyResponse>builder()
            .success(true)
            .message("Company fetched successfully")
            .data(companyService.getMyCompany())
            .build();
}

    @GetMapping("/{id}")
    public ApiResponse<CompanyResponse> getCompany(
            @PathVariable Long id) {

        return ApiResponse.success(
                "Company fetched successfully",
                companyService.getCompany(id)
        );
    }

    @GetMapping
    public ApiResponse<List<CompanyResponse>> getAllCompanies() {

        return ApiResponse.success(
                "Companies fetched successfully",
                companyService.getAllCompanies()
        );
    }
}