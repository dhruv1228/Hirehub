package com.hirehub.dashboard.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecruiterDashboardResponse {

    private long totalCompanies;

    private long totalJobs;

    private long activeJobs;

    private long closedJobs;

    private long totalApplications;

    private long shortlisted;

    private long rejected;

    private long hired;
}