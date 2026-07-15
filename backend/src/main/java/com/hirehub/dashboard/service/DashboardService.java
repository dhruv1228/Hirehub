package com.hirehub.dashboard.service;

import com.hirehub.dashboard.dto.RecruiterDashboardResponse;
import com.hirehub.dashboard.dto.CandidateDashboardResponse;
import com.hirehub.dashboard.dto.HomeStatsResponse;

public interface DashboardService {

    RecruiterDashboardResponse getRecruiterDashboard();
    CandidateDashboardResponse getCandidateDashboard();
    HomeStatsResponse getHomeStats();

}