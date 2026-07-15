package com.hirehub.company.service;

import com.hirehub.company.dto.CompanyResponse;
import com.hirehub.company.dto.CreateCompanyRequest;
import com.hirehub.company.dto.UpdateCompanyRequest;

import java.util.List;

public interface CompanyService {

    CompanyResponse createCompany(CreateCompanyRequest request);

    CompanyResponse getCompany(Long id);

    List<CompanyResponse> getAllCompanies();

    CompanyResponse updateCompany(Long id, UpdateCompanyRequest request);

    void deleteCompany(Long id);

    CompanyResponse getMyCompany();


}