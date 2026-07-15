package com.hirehub.savedjob.controller;

import com.hirehub.common.response.ApiResponse;
import com.hirehub.savedjob.dto.SavedJobResponse;
import com.hirehub.savedjob.service.SavedJobService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/saved-jobs")
@RequiredArgsConstructor
public class SavedJobController {

    private final SavedJobService savedJobService;

    @PostMapping("/{jobId}")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ApiResponse<Void> saveJob(@PathVariable Long jobId) {

        savedJobService.saveJob(jobId);

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Job saved successfully")
                .build();
    }

    @DeleteMapping("/{jobId}")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ApiResponse<Void> removeJob(@PathVariable Long jobId) {

        savedJobService.removeJob(jobId);

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Saved job removed successfully")
                .build();
    }

    @GetMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    public ApiResponse<List<SavedJobResponse>> getSavedJobs() {

        return ApiResponse.<List<SavedJobResponse>>builder()
                .success(true)
                .message("Saved jobs fetched successfully")
                .data(savedJobService.getSavedJobs())
                .build();
    }
}