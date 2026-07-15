package com.hirehub.savedjob.service.impl;

import com.hirehub.common.security.SecurityUtils;
import com.hirehub.job.entity.Job;
import com.hirehub.job.repository.JobRepository;
import com.hirehub.savedjob.dto.SavedJobResponse;
import com.hirehub.savedjob.entity.SavedJob;
import com.hirehub.savedjob.repository.SavedJobRepository;
import com.hirehub.savedjob.service.SavedJobService;
import com.hirehub.user.entity.User;
import com.hirehub.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SavedJobServiceImpl implements SavedJobService {

    private final SavedJobRepository savedJobRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    @Override
    public void saveJob(Long jobId) {

        String email = SecurityUtils.getCurrentUserEmail();

        User candidate = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        if (savedJobRepository.existsByCandidateAndJob(candidate, job)) {
            throw new RuntimeException("Job already saved.");
        }

        SavedJob savedJob = SavedJob.builder()
                .candidate(candidate)
                .job(job)
                .build();

        savedJobRepository.save(savedJob);
    }

    @Override
    public void removeJob(Long jobId) {

        String email = SecurityUtils.getCurrentUserEmail();

        User candidate = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        SavedJob savedJob = savedJobRepository
                .findByCandidateAndJob(candidate, job)
                .orElseThrow(() -> new RuntimeException("Saved job not found"));

        savedJobRepository.delete(savedJob);
    }

    @Override
    public List<SavedJobResponse> getSavedJobs() {

        String email = SecurityUtils.getCurrentUserEmail();

        User candidate = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        return savedJobRepository.findByCandidate(candidate)
                .stream()
                .map(saved -> SavedJobResponse.builder()
                        .id(saved.getId())
                        .jobId(saved.getJob().getId())
                        .title(saved.getJob().getTitle())
                        .company(saved.getJob().getCompany().getName())
                        .location(saved.getJob().getLocation())
                        .workMode(saved.getJob().getWorkMode().name())
                        .build())
                .toList();
    }
}