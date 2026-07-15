package com.hirehub.company.repository;

import com.hirehub.company.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.hirehub.user.entity.User;


public interface CompanyRepository extends JpaRepository<Company, Long> {

Optional<Company> findByRecruiter(User recruiter);

    long count();

    Optional<Company> findByName(String name);

    boolean existsByName(String name);
}