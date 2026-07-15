package com.hirehub.job.repository;

import com.hirehub.job.entity.Job;
import com.hirehub.job.enums.JobType;
import com.hirehub.job.enums.WorkMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hirehub.job.enums.JobStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.hirehub.user.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.List;

public interface JobRepository
        extends JpaRepository<Job, Long>,
                JpaSpecificationExecutor<Job> {


    List<Job> findByCompanyRecruiter(User recruiter);

    long countByStatus(JobStatus status);

    Page<Job> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<Job> findByLocationContainingIgnoreCase(String location, Pageable pageable);

    Page<Job> findByJobType(JobType jobType, Pageable pageable);

    Page<Job> findByWorkMode(WorkMode workMode, Pageable pageable);

    Page<Job> findByCompanyId(Long companyId, Pageable pageable);
}