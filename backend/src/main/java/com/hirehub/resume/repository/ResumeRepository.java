package com.hirehub.resume.repository;

import com.hirehub.resume.entity.Resume;
import com.hirehub.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    Optional<Resume> findByCandidate(User candidate);
}