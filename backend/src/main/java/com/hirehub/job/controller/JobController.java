package com.hirehub.job.controller;

import com.hirehub.common.response.ApiResponse;
import com.hirehub.job.dto.CreateJobRequest;
import com.hirehub.job.dto.JobResponse;
import com.hirehub.job.dto.UpdateJobRequest;
import com.hirehub.job.enums.JobType;
import com.hirehub.job.enums.WorkMode;
import com.hirehub.job.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping("/my")
        @PreAuthorize("hasRole('RECRUITER')")
        public ApiResponse<List<JobResponse>> getMyJobs() {

        return ApiResponse.<List<JobResponse>>builder()
                .success(true)
                .message("Recruiter jobs fetched successfully")
                .data(jobService.getMyJobs())
                .build();
        }

    @PostMapping
    @PreAuthorize("hasRole('RECRUITER')")
    public ApiResponse<JobResponse> createJob(
            @Valid @RequestBody CreateJobRequest request) {

        return ApiResponse.<JobResponse>builder()
                .success(true)
                .message("Job created successfully")
                .data(jobService.createJob(request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<JobResponse> getJob(
            @PathVariable Long id) {

        return ApiResponse.<JobResponse>builder()
                .success(true)
                .message("Job fetched successfully")
                .data(jobService.getJob(id))
                .build();
    }

    @GetMapping
public ApiResponse<Page<JobResponse>> getAllJobs(

        @RequestParam(defaultValue = "0") int page,

        @RequestParam(defaultValue = "10") int size
) {

    return ApiResponse.success(
            "Jobs fetched successfully",
            jobService.getAllJobs(page, size)
    );
}

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('RECRUITER')")
    public ApiResponse<JobResponse> updateJob(
            @PathVariable Long id,
            @RequestBody UpdateJobRequest request) {

        return ApiResponse.<JobResponse>builder()
                .success(true)
                .message("Job updated successfully")
                .data(jobService.updateJob(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('RECRUITER')")
    public ApiResponse<Void> deleteJob(
            @PathVariable Long id) {

        jobService.deleteJob(id);

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Job deleted successfully")
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<Page<JobResponse>> searchJobs(

            @RequestParam String title,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size
    ) {

        return ApiResponse.<Page<JobResponse>>builder()
                .success(true)
                .message("Jobs fetched successfully")
                .data(jobService.searchJobs(title, page, size))
                .build();
    }

    @GetMapping("/location")
    public ApiResponse<Page<JobResponse>> jobsByLocation(

            @RequestParam String location,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size
    ) {

        return ApiResponse.<Page<JobResponse>>builder()
                .success(true)
                .message("Jobs fetched successfully")
                .data(jobService.jobsByLocation(location, page, size))
                .build();
    }

    @GetMapping("/type")
    public ApiResponse<Page<JobResponse>> jobsByType(

            @RequestParam JobType jobType,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size
    ) {

        return ApiResponse.<Page<JobResponse>>builder()
                .success(true)
                .message("Jobs fetched successfully")
                .data(jobService.jobsByType(jobType, page, size))
                .build();
    }

    @GetMapping("/filter")
        public ApiResponse<Page<JobResponse>> filterJobs(

                @RequestParam(required = false) String title,

                @RequestParam(required = false) String location,

                @RequestParam(required = false) JobType jobType,

                @RequestParam(required = false) WorkMode workMode,

                @RequestParam(defaultValue = "0") int page,

                @RequestParam(defaultValue = "10") int size
        ) {

        return ApiResponse.success(
        "Jobs fetched successfully",
        jobService.filterJobs(
                title,
                location,
                jobType,
                workMode,
                page,
                size
        )
        );
        }

    @GetMapping("/work-mode")
    public ApiResponse<Page<JobResponse>> jobsByWorkMode(

            @RequestParam WorkMode workMode,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size
    ) {

        return ApiResponse.<Page<JobResponse>>builder()
                .success(true)
                .message("Jobs fetched successfully")
                .data(jobService.jobsByWorkMode(workMode, page, size))
                .build();
    }

    @GetMapping("/company/{companyId}")
    public ApiResponse<Page<JobResponse>> jobsByCompany(

            @PathVariable Long companyId,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size
    ) {

        return ApiResponse.<Page<JobResponse>>builder()
                .success(true)
                .message("Jobs fetched successfully")
                .data(jobService.jobsByCompany(companyId, page, size))
                .build();
    }
}