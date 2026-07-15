package com.hirehub.application.service;

import com.hirehub.application.dto.*;

import java.util.List;

public interface ApplicationService {

    ApplicationResponse applyJob(ApplyJobRequest request);

    List<ApplicationResponse> getMyApplications();

    List<ApplicationResponse> getApplicationsByJob(Long jobId);

    ApplicationResponse updateStatus(
            Long applicationId,
            UpdateApplicationStatusRequest request);
}