package com.hirehub.profile.controller;

import com.hirehub.common.response.ApiResponse;
import com.hirehub.profile.dto.CandidateProfileRequest;
import com.hirehub.profile.dto.CandidateProfileResponse;
import com.hirehub.profile.service.CandidateProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final CandidateProfileService profileService;

    @GetMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    public ApiResponse<CandidateProfileResponse> getMyProfile() {

        return ApiResponse.<CandidateProfileResponse>builder()
                .success(true)
                .message("Profile fetched successfully")
                .data(profileService.getMyProfile())
                .build();
    }

    @PutMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    public ApiResponse<CandidateProfileResponse> updateProfile(
            @Valid @RequestBody CandidateProfileRequest request) {

        return ApiResponse.<CandidateProfileResponse>builder()
                .success(true)
                .message("Profile updated successfully")
                .data(profileService.updateProfile(request))
                .build();
    }
}