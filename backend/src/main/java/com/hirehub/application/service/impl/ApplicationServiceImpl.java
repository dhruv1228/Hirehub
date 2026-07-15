package com.hirehub.application.service.impl;

import com.hirehub.application.dto.*;
import com.hirehub.application.repository.ApplicationRepository;
import com.hirehub.application.service.ApplicationService;
import com.hirehub.common.security.SecurityUtils;
import com.hirehub.job.repository.JobRepository;
import com.hirehub.notification.enums.NotificationType;
import com.hirehub.notification.service.NotificationService;
import com.hirehub.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import com.hirehub.application.entity.Application;
import com.hirehub.application.enums.ApplicationStatus;
import com.hirehub.job.entity.Job;
import com.hirehub.user.entity.User;

import com.hirehub.common.email.EmailService;



@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;
    private final EmailService emailService;
    private final NotificationService notificationService;

   @Override
    public ApplicationResponse applyJob(ApplyJobRequest request) {
        String email = SecurityUtils.getCurrentUserEmail();

        User candidate = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Candidate not found"));

        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() ->
                        new RuntimeException("Job not found"));

        if (applicationRepository.existsByCandidateAndJob(candidate, job)) {
            throw new RuntimeException("You have already applied for this job.");
        }

        Application application = Application.builder()
                .candidate(candidate)
                .job(job)
                .resumeUrl(request.getResumeUrl())
                .coverLetter(request.getCoverLetter())
                .status(ApplicationStatus.APPLIED)
                .build();

        Application saved = applicationRepository.save(application);

notificationService.createNotification(
    candidate.getId(),
    "Application Submitted",
    "You have successfully applied for " + job.getTitle(),
    NotificationType.JOB_APPLICATION
);

notificationService.createNotification(
    job.getCompany().getRecruiter().getId(),
    "New Job Application",
    candidate.getFirstName() + " " + candidate.getLastName()
            + " applied for " + job.getTitle(),
    NotificationType.JOB_APPLICATION
);
        emailService.sendEmail(
                candidate.getEmail(),
                "Application Submitted",
                """
                Hi %s,

                Your application for the position:

                %s

                has been submitted successfully.

                Good luck!

                HireHub Team
                """.formatted(
                        candidate.getFirstName(),
                        job.getTitle()
                )
        );

        return ApplicationResponse.builder()
                .id(saved.getId())
                .candidateId(candidate.getId())
                .candidateName(candidate.getFirstName() + " " + candidate.getLastName())
                .jobId(job.getId())
                .companyName(job.getCompany().getName())
                .jobTitle(job.getTitle())
                .status(saved.getStatus())
                .resumeUrl(saved.getResumeUrl())
                .coverLetter(saved.getCoverLetter())
                .appliedAt(application.getCreatedAt())
                .build();
    }

    @Override
    public List<ApplicationResponse> getMyApplications() {

        String email = SecurityUtils.getCurrentUserEmail();

        User candidate = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Candidate not found"));

        return applicationRepository.findByCandidate(candidate)
                .stream()
                .map(app -> ApplicationResponse.builder()
                        .id(app.getId())
                        .candidateId(candidate.getId())
                        .candidateName(candidate.getFirstName() + " " + candidate.getLastName())
                        .jobId(app.getJob().getId())
                        .jobTitle(app.getJob().getTitle())
                        .status(app.getStatus())
                        .resumeUrl(app.getResumeUrl())
                        .coverLetter(app.getCoverLetter())
                        .companyName(app.getJob().getCompany().getName())
                        .appliedAt(app.getCreatedAt())

                        .build())
                .toList();
    }

    @Override
    public List<ApplicationResponse> getApplicationsByJob(Long jobId) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new RuntimeException("Job not found"));

        return applicationRepository.findByJob(job)
                .stream()
                .map(app -> {

                    User candidate = app.getCandidate();

                    return ApplicationResponse.builder()
                            .id(app.getId())
                            .candidateId(candidate.getId())
                            .candidateName(
                                    candidate.getFirstName() + " " +
                                    candidate.getLastName())
                            .jobId(job.getId())
                            .jobTitle(job.getTitle())
                            .status(app.getStatus())
                            .resumeUrl(app.getResumeUrl())
                            .coverLetter(app.getCoverLetter())
                            .companyName(app.getJob().getCompany().getName())
                            .appliedAt(app.getCreatedAt())

                            .build();
                })
                .toList();
    }

    @Override
    public ApplicationResponse updateStatus(
            Long applicationId,
            UpdateApplicationStatusRequest request) {

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() ->
                        new RuntimeException("Application not found"));

        application.setStatus(request.getStatus());

        Application updated = applicationRepository.save(application);
        

        User candidate = updated.getCandidate();
        Job job = updated.getJob();
        notificationService.createNotification(
            candidate.getId(),
            "Application Status Updated",
            "Your application for " + job.getTitle()
                    + " is now " + updated.getStatus(),
            NotificationType.APPLICATION_STATUS
        );

        try {
            emailService.sendEmail(
                    candidate.getEmail(),
                    "Application Status Updated",
                    """
                    Hi %s,

                    Your application for:

                    %s

                    has been updated.

                    New Status: %s

                    Regards,
                    HireHub Team
                    """.formatted(
                            candidate.getFirstName(),
                            job.getTitle(),
                            updated.getStatus()
                    )
            );
        } catch (Exception ex) {
            ex.printStackTrace(); // Replace with logger later
        }


        return ApplicationResponse.builder()
                .id(updated.getId())
                .candidateId(candidate.getId())
                .candidateName(candidate.getFirstName() + " " + candidate.getLastName())
                .jobId(job.getId())
                .jobTitle(job.getTitle())
                .status(updated.getStatus())
                .resumeUrl(updated.getResumeUrl())
                .coverLetter(updated.getCoverLetter())
                .companyName(application.getJob().getCompany().getName())
                .appliedAt(application.getCreatedAt())
                .build();
    }
}