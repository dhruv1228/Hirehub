package com.hirehub.dashboard.controller;

import com.hirehub.common.response.ApiResponse;
import com.hirehub.dashboard.dto.RecruiterDashboardResponse;
import com.hirehub.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.hirehub.dashboard.dto.CandidateDashboardResponse;
import com.hirehub.dashboard.dto.HomeStatsResponse;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/home-stats")
    public ApiResponse<HomeStatsResponse> homeStats() {

        return ApiResponse.<HomeStatsResponse>builder()
                .success(true)
                .message("Home stats fetched successfully")
                .data(dashboardService.getHomeStats())
                .build();
    }

    @GetMapping("/recruiter")
    @PreAuthorize("hasAnyRole('RECRUITER','ADMIN')")
    public ApiResponse<RecruiterDashboardResponse> recruiterDashboard() {

        return ApiResponse.<RecruiterDashboardResponse>builder()
                .success(true)
                .message("Dashboard fetched successfully")
                .data(dashboardService.getRecruiterDashboard())
                .build();
    }
    @GetMapping("/candidate")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ApiResponse<CandidateDashboardResponse> candidateDashboard() {

        return ApiResponse.<CandidateDashboardResponse>builder()
                .success(true)
                .message("Dashboard fetched successfully")
                .data(dashboardService.getCandidateDashboard())
                .build();
    }
}