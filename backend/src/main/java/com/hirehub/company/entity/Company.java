package com.hirehub.company.entity;

import com.hirehub.common.entity.BaseEntity;
import com.hirehub.company.enums.CompanySize;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.EnumType;
import com.hirehub.job.entity.Job;
import java.util.List;
import jakarta.persistence.CascadeType;

import com.hirehub.user.entity.User;




@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "companies")

public class Company extends BaseEntity {

    @Column(nullable = false, length = 150)
    private String name;

    @Column(length = 2000)
    private String description;

    @Column(length = 255)
    private String website;

    @Column(length = 100)
    private String industry;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CompanySize companySize;

    @Column(length = 150)
    private String headquarters;

    @Column(length = 500)
    private String logoUrl;

    @OneToMany(
        mappedBy = "company",
        cascade = CascadeType.ALL
    )
    private List<Job> jobs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruiter_id", nullable = false)
    private User recruiter;
}