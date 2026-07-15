package com.hirehub.application.repository;

import com.hirehub.application.entity.Application;
import com.hirehub.job.entity.Job;
import com.hirehub.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hirehub.application.enums.ApplicationStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Modifying
    @Transactional
    void deleteByJob(Job job);

    long countByCandidate(User candidate);

    long countByCandidateAndStatus(User candidate, ApplicationStatus status);

    long countByStatus(ApplicationStatus status);

    List<Application> findByCandidate(User candidate);

    List<Application> findByJob(Job job);

    boolean existsByCandidateAndJob(User candidate, Job job);
}