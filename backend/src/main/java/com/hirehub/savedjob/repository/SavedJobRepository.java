package com.hirehub.savedjob.repository;

import com.hirehub.savedjob.entity.SavedJob;
import com.hirehub.job.entity.Job;
import com.hirehub.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SavedJobRepository extends JpaRepository<SavedJob, Long> {

    long countByCandidate(User candidate);

    List<SavedJob> findByCandidate(User candidate);

    Optional<SavedJob> findByCandidateAndJob(User candidate, Job job);

    boolean existsByCandidateAndJob(User candidate, Job job);
}