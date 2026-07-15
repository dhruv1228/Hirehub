package com.hirehub.application.controller;

import com.hirehub.application.dto.ApplicationResponse;
import com.hirehub.application.dto.ApplyJobRequest;
import com.hirehub.application.dto.UpdateApplicationStatusRequest;
import com.hirehub.application.service.ApplicationService;
import com.hirehub.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    public ApiResponse<ApplicationResponse> applyJob(
            @Valid @RequestBody ApplyJobRequest request) {

        return ApiResponse.<ApplicationResponse>builder()
                .success(true)
                .message("Applied successfully")
                .data(applicationService.applyJob(request))
                .build();
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ApiResponse<List<ApplicationResponse>> getMyApplications() {

        return ApiResponse.<List<ApplicationResponse>>builder()
                .success(true)
                .message("Applications fetched successfully")
                .data(applicationService.getMyApplications())
                .build();
    }

    @GetMapping("/job/{jobId}")
    @PreAuthorize("hasAnyRole('RECRUITER','ADMIN')")
    public ApiResponse<List<ApplicationResponse>> getApplicationsByJob(
            @PathVariable Long jobId) {

        return ApiResponse.<List<ApplicationResponse>>builder()
                .success(true)
                .message("Applications fetched successfully")
                .data(applicationService.getApplicationsByJob(jobId))
                .build();
    }

    @PatchMapping("/{applicationId}/status")
    @PreAuthorize("hasAnyRole('RECRUITER','ADMIN')")
    public ApiResponse<ApplicationResponse> updateStatus(
            @PathVariable Long applicationId,
            @Valid @RequestBody UpdateApplicationStatusRequest request) {

        return ApiResponse.<ApplicationResponse>builder()
                .success(true)
                .message("Application status updated successfully")
                .data(applicationService.updateStatus(applicationId, request))
                .build();
    }
}