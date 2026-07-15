package com.hirehub.profile.repository;

import com.hirehub.profile.entity.CandidateProfile;
import com.hirehub.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CandidateProfileRepository
        extends JpaRepository<CandidateProfile, Long> {

    Optional<CandidateProfile> findByCandidate(User candidate);
}