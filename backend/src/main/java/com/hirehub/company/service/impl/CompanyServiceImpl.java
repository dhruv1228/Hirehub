package com.hirehub.company.service.impl;

import com.hirehub.company.dto.CompanyResponse;
import com.hirehub.company.dto.CreateCompanyRequest;
import com.hirehub.company.dto.UpdateCompanyRequest;
import com.hirehub.company.entity.Company;
import com.hirehub.company.repository.CompanyRepository;
import com.hirehub.company.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import com.hirehub.common.security.SecurityUtils;
import com.hirehub.user.entity.User;
import com.hirehub.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    private final UserRepository userRepository;

    @Override
public CompanyResponse createCompany(CreateCompanyRequest request) {


    String email = SecurityUtils.getCurrentUserEmail();

User recruiter = userRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("Recruiter not found"));

        if (companyRepository.findByRecruiter(recruiter).isPresent()) {
    throw new RuntimeException("You have already created a company.");
}
if (companyRepository.existsByName(request.getName())) {
        throw new RuntimeException("Company already exists");
    }

    Company company = Company.builder()
            .name(request.getName())
            .description(request.getDescription())
            .website(request.getWebsite())
            .industry(request.getIndustry())
            .companySize(request.getCompanySize())
            .headquarters(request.getHeadquarters())
            .recruiter(recruiter)
            .build();

    Company savedCompany = companyRepository.save(company);

    return CompanyResponse.builder()
            .id(savedCompany.getId())
            .name(savedCompany.getName())
            .description(savedCompany.getDescription())
            .website(savedCompany.getWebsite())
            .industry(savedCompany.getIndustry())
            .companySize(savedCompany.getCompanySize())
            .headquarters(savedCompany.getHeadquarters())
            .logoUrl(savedCompany.getLogoUrl())
            .build();
}

   @Override
    public CompanyResponse getCompany(Long id) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        return CompanyResponse.builder()
                .id(company.getId())
                .name(company.getName())
                .description(company.getDescription())
                .website(company.getWebsite())
                .industry(company.getIndustry())
                .companySize(company.getCompanySize())
                .headquarters(company.getHeadquarters())
                .logoUrl(company.getLogoUrl())
                .build();
    }

    @Override
public CompanyResponse getMyCompany() {

    String email = SecurityUtils.getCurrentUserEmail();

    User recruiter = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Recruiter not found"));

    Company company = companyRepository.findByRecruiter(recruiter)
            .orElseThrow(() -> new RuntimeException("Company not found"));

    return CompanyResponse.builder()
            .id(company.getId())
            .name(company.getName())
            .description(company.getDescription())
            .website(company.getWebsite())
            .industry(company.getIndustry())
            .companySize(company.getCompanySize())
            .headquarters(company.getHeadquarters())
            .logoUrl(company.getLogoUrl())
            .build();
}

    @Override
    public List<CompanyResponse> getAllCompanies() {

        return companyRepository.findAll()
                .stream()
                .map(company -> CompanyResponse.builder()
                        .id(company.getId())
                        .name(company.getName())
                        .description(company.getDescription())
                        .website(company.getWebsite())
                        .industry(company.getIndustry())
                        .companySize(company.getCompanySize())
                        .headquarters(company.getHeadquarters())
                        .logoUrl(company.getLogoUrl())
                        .build())
                .toList();
    }

    @Override
public CompanyResponse updateCompany(Long id, UpdateCompanyRequest request) {

    Company company = companyRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Company not found"));

    company.setDescription(request.getDescription());
    company.setWebsite(request.getWebsite());
    company.setIndustry(request.getIndustry());
    company.setCompanySize(request.getCompanySize());
    company.setHeadquarters(request.getHeadquarters());
    company.setLogoUrl(request.getLogoUrl());

    Company updated = companyRepository.save(company);

    return CompanyResponse.builder()
            .id(updated.getId())
            .name(updated.getName())
            .description(updated.getDescription())
            .website(updated.getWebsite())
            .industry(updated.getIndustry())
            .companySize(updated.getCompanySize())
            .headquarters(updated.getHeadquarters())
            .logoUrl(updated.getLogoUrl())
            .build();
}

    @Override
public void deleteCompany(Long id) {

    Company company = companyRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Company not found"));

    companyRepository.delete(company);
}
}