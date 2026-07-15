package com.hirehub.job.service.impl;

import com.hirehub.job.dto.*;
import com.hirehub.job.service.JobService;
import com.hirehub.user.repository.UserRepository;
import com.hirehub.job.repository.JobRepository;
import com.hirehub.application.repository.ApplicationRepository;
import com.hirehub.common.security.SecurityUtils;
import com.hirehub.company.entity.Company;
import com.hirehub.company.repository.CompanyRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.hirehub.job.entity.Job;
import com.hirehub.job.enums.JobType;
import com.hirehub.job.enums.WorkMode;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.hirehub.user.entity.User;

import com.hirehub.job.specification.JobSpecification;

import org.springframework.data.domain.Pageable;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;



    @Override
    public JobResponse createJob(CreateJobRequest request) {

        String email = SecurityUtils.getCurrentUserEmail();

        User recruiter = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Recruiter not found"));

        Company company = companyRepository.findByRecruiter(recruiter)
                .orElseThrow(() -> new RuntimeException("Please create your company first"));

        Job job = Job.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .location(request.getLocation())
                .salary(request.getSalary())
                .experience(request.getExperience())
                .jobType(request.getJobType())
                .workMode(request.getWorkMode())
                .company(company)
                .build();

        Job savedJob = jobRepository.save(job);

        return JobResponse.builder()
                .id(savedJob.getId())
                .title(savedJob.getTitle())
                .description(savedJob.getDescription())
                .location(savedJob.getLocation())
                .salary(savedJob.getSalary())
                .experience(savedJob.getExperience())
                .jobType(savedJob.getJobType())
                .workMode(savedJob.getWorkMode())
                .status(savedJob.getStatus())
                .companyId(company.getId())
                .companyName(company.getName())
                .build();
    }

    @Override
    public JobResponse getJob(Long id) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        boolean applied = false;

        try {
            String email = SecurityUtils.getCurrentUserEmail();

            User candidate = userRepository.findByEmail(email)
                    .orElse(null);

            if (candidate != null) {
                applied = applicationRepository
                        .existsByCandidateAndJob(candidate, job);
            }

        } catch (Exception ignored) {
        }

        return JobResponse.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .location(job.getLocation())
                .salary(job.getSalary())
                .experience(job.getExperience())
                .jobType(job.getJobType())
                .workMode(job.getWorkMode())
                .status(job.getStatus())
                .companyId(job.getCompany().getId())
                .companyName(job.getCompany().getName())
                .applied(applied)
                .build();
    }

@Override
public Page<JobResponse> getAllJobs(int page, int size) {

    Pageable pageable = PageRequest.of(page, size);

    return jobRepository
            .findAll(pageable)
            .map(this::mapToResponse);
}
    @Override
    public List<JobResponse> getMyJobs() {

        String email = SecurityUtils.getCurrentUserEmail();

        User recruiter = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Recruiter not found"));

        return jobRepository.findByCompanyRecruiter(recruiter)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public JobResponse updateJob(Long id, UpdateJobRequest request) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        if (request.getTitle() != null)
            job.setTitle(request.getTitle());

        if (request.getDescription() != null)
            job.setDescription(request.getDescription());

        if (request.getLocation() != null)
            job.setLocation(request.getLocation());

        if (request.getSalary() != null)
            job.setSalary(request.getSalary());

        if (request.getExperience() != null)
            job.setExperience(request.getExperience());

        if (request.getJobType() != null)
            job.setJobType(request.getJobType());

        if (request.getWorkMode() != null)
            job.setWorkMode(request.getWorkMode());

        if (request.getStatus() != null)
            job.setStatus(request.getStatus());

        Job updated = jobRepository.save(job);

        return JobResponse.builder()
                .id(updated.getId())
                .title(updated.getTitle())
                .description(updated.getDescription())
                .location(updated.getLocation())
                .salary(updated.getSalary())
                .experience(updated.getExperience())
                .jobType(updated.getJobType())
                .workMode(updated.getWorkMode())
                .status(updated.getStatus())
                .companyId(updated.getCompany().getId())
                .companyName(updated.getCompany().getName())
                .build();
    }

    @Override
    @Transactional
    public void deleteJob(Long id) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        // Delete all applications for this job first
        applicationRepository.deleteByJob(job);

        // Now delete the job
        jobRepository.delete(job);
    }

   @Override
    public Page<JobResponse> searchJobs(String title, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return jobRepository
                .findByTitleContainingIgnoreCase(title, pageable)
                .map(this::mapToResponse);
    }

    @Override
    public Page<JobResponse> jobsByLocation(String location, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return jobRepository
                .findByLocationContainingIgnoreCase(location, pageable)
                .map(this::mapToResponse);
    }

    @Override
    public Page<JobResponse> jobsByType(JobType jobType, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return jobRepository
                .findByJobType(jobType, pageable)
                .map(this::mapToResponse);
    }

    @Override
    public Page<JobResponse> jobsByWorkMode(WorkMode workMode, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return jobRepository
                .findByWorkMode(workMode, pageable)
                .map(this::mapToResponse);
    }

    @Override
public Page<JobResponse> filterJobs(
   
        String title,
        String location,
        JobType jobType,
        WorkMode workMode,
        int page,
        int size
) {

    Pageable pageable = PageRequest.of(page, size);

    return jobRepository.findAll(
            JobSpecification.filterJobs(
                    title,
                    location,
                    jobType,
                    workMode
            ),
            pageable
    ).map(this::mapToResponse);
}

    @Override
    public Page<JobResponse> jobsByCompany(Long companyId, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return jobRepository
                .findByCompanyId(companyId, pageable)
                .map(this::mapToResponse);
    }

private JobResponse mapToResponse(Job job) {

    boolean applied = false;

    try {

        String email = SecurityUtils.getCurrentUserEmail();

        User candidate = userRepository
                .findByEmail(email)
                .orElse(null);

        if (candidate != null) {

            applied = applicationRepository
                    .existsByCandidateAndJob(candidate, job);

        }

    } catch (Exception ignored) {
    }

    return JobResponse.builder()
            .id(job.getId())
            .title(job.getTitle())
            .description(job.getDescription())
            .location(job.getLocation())
            .salary(job.getSalary())
            .experience(job.getExperience())
            .jobType(job.getJobType())
            .workMode(job.getWorkMode())
            .status(job.getStatus())
            .companyId(job.getCompany().getId())
            .companyName(job.getCompany().getName())
            .applied(applied)
            .build();
}
}