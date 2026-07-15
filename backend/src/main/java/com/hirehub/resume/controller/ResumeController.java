package com.hirehub.resume.controller;

import com.hirehub.common.response.ApiResponse;
import com.hirehub.resume.dto.ResumeResponse;
import com.hirehub.resume.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/resume")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping("/upload")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ApiResponse<ResumeResponse> uploadResume(
            @RequestParam("file") MultipartFile file) {

        return ApiResponse.<ResumeResponse>builder()
                .success(true)
                .message("Resume uploaded successfully")
                .data(resumeService.uploadResume(file))
                .build();
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ApiResponse<ResumeResponse> getMyResume() {

        return ApiResponse.<ResumeResponse>builder()
                .success(true)
                .message("Resume fetched successfully")
                .data(resumeService.getMyResume())
                .build();
    }

    @DeleteMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    public ApiResponse<Void> deleteResume() {

        resumeService.deleteResume();

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Resume deleted successfully")
                .build();
    }
}