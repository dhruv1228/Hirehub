package com.hirehub.profile.service;

import com.hirehub.profile.dto.CandidateProfileRequest;
import com.hirehub.profile.dto.CandidateProfileResponse;

public interface CandidateProfileService {

    CandidateProfileResponse getMyProfile();

    CandidateProfileResponse updateProfile(
            CandidateProfileRequest request
    );
}