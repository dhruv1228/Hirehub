package com.hirehub.profile.service.impl;

import com.hirehub.common.security.SecurityUtils;
import com.hirehub.profile.dto.CandidateProfileRequest;
import com.hirehub.profile.dto.CandidateProfileResponse;
import com.hirehub.profile.entity.CandidateProfile;
import com.hirehub.profile.repository.CandidateProfileRepository;
import com.hirehub.profile.service.CandidateProfileService;
import com.hirehub.user.entity.User;
import com.hirehub.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateProfileServiceImpl
        implements CandidateProfileService {

    private final CandidateProfileRepository profileRepository;
    private final UserRepository userRepository;

    @Override
    public CandidateProfileResponse getMyProfile() {

        String email = SecurityUtils.getCurrentUserEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        CandidateProfile profile = profileRepository
                .findByCandidate(user)
                .orElseGet(() -> {

                    CandidateProfile newProfile =
                            CandidateProfile.builder()
                                    .candidate(user)
                                    .build();

                    return profileRepository.save(newProfile);
                });

        return mapToResponse(profile);
    }

    @Override
    public CandidateProfileResponse updateProfile(
            CandidateProfileRequest request) {

        String email = SecurityUtils.getCurrentUserEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        CandidateProfile profile = profileRepository
                .findByCandidate(user)
                .orElseGet(() ->
                        CandidateProfile.builder()
                                .candidate(user)
                                .build());

        profile.setBio(request.getBio());
        profile.setSkills(request.getSkills());
        profile.setEducation(request.getEducation());
        profile.setExperience(request.getExperience());
        profile.setLinkedin(request.getLinkedin());
        profile.setGithub(request.getGithub());
        profile.setPortfolio(request.getPortfolio());
        profile.setLocation(request.getLocation());

        CandidateProfile saved =
                profileRepository.save(profile);

        return mapToResponse(saved);
    }

    private CandidateProfileResponse mapToResponse(
            CandidateProfile profile) {

        User user = profile.getCandidate();

        return CandidateProfileResponse.builder()
                .id(profile.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .profilePicture(user.getProfilePicture())
                .bio(profile.getBio())
                .skills(profile.getSkills())
                .education(profile.getEducation())
                .experience(profile.getExperience())
                .linkedin(profile.getLinkedin())
                .github(profile.getGithub())
                .portfolio(profile.getPortfolio())
                .location(profile.getLocation())
                .build();
    }
}