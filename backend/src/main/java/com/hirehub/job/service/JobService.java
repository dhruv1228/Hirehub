package com.hirehub.job.service;

import com.hirehub.job.dto.*;
import com.hirehub.job.enums.JobType;
import com.hirehub.job.enums.WorkMode;

import java.util.List;

import org.springframework.data.domain.Page;



public interface JobService {

    List<JobResponse> getMyJobs();

    JobResponse createJob(CreateJobRequest request);

    JobResponse getJob(Long id);

    Page<JobResponse> getAllJobs(int page, int size);

    JobResponse updateJob(Long id, UpdateJobRequest request);

    void deleteJob(Long id);

    Page<JobResponse> searchJobs(String title, int page, int size);

    Page<JobResponse> jobsByLocation(String location, int page, int size);

    Page<JobResponse> jobsByType(JobType jobType, int page, int size);

    Page<JobResponse> jobsByWorkMode(WorkMode workMode, int page, int size);

    Page<JobResponse> jobsByCompany(Long companyId, int page, int size);

    Page<JobResponse> filterJobs(
        String title,
        String location,
        JobType jobType,
        WorkMode workMode,
        int page,
        int size
);
}