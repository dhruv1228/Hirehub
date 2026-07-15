package com.hirehub.dashboard.service.impl;

import com.hirehub.application.enums.ApplicationStatus;
import com.hirehub.application.repository.ApplicationRepository;
import com.hirehub.common.security.SecurityUtils;
import com.hirehub.dashboard.dto.CandidateDashboardResponse;
import com.hirehub.dashboard.dto.RecruiterDashboardResponse;
import com.hirehub.dashboard.service.DashboardService;
import com.hirehub.job.enums.JobStatus;
import com.hirehub.job.repository.JobRepository;
import com.hirehub.notification.repository.NotificationRepository;
import com.hirehub.savedjob.repository.SavedJobRepository;
import com.hirehub.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.hirehub.user.repository.UserRepository;
import com.hirehub.user.entity.User;
import com.hirehub.dashboard.dto.HomeStatsResponse;
import com.hirehub.user.entity.Role;


@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final CompanyRepository companyRepository;
    private final JobRepository jobRepository;
    private final ApplicationRepository applicationRepository;
    private final SavedJobRepository savedJobRepository;
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Override
        public HomeStatsResponse getHomeStats() {

        return HomeStatsResponse.builder()
                .jobs(jobRepository.count())
                .companies(companyRepository.count())
                .candidates(userRepository.countByRole(Role.CANDIDATE))
                .recruiters(userRepository.countByRole(Role.RECRUITER))
                .build();
        }

    @Override
    public RecruiterDashboardResponse getRecruiterDashboard() {

        return RecruiterDashboardResponse.builder()
                .totalCompanies(companyRepository.count())
                .totalJobs(jobRepository.count())
                .activeJobs(jobRepository.countByStatus(JobStatus.OPEN))
                .closedJobs(jobRepository.countByStatus(JobStatus.CLOSED))
                .totalApplications(applicationRepository.count())
                .shortlisted(applicationRepository.countByStatus(ApplicationStatus.SHORTLISTED))
                .rejected(applicationRepository.countByStatus(ApplicationStatus.REJECTED))
                .hired(applicationRepository.countByStatus(ApplicationStatus.HIRED))
                .build();
    }
    @Override
    public CandidateDashboardResponse getCandidateDashboard() {

        String email = SecurityUtils.getCurrentUserEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        long applications =
                applicationRepository.countByCandidate(user);

        long shortlisted =
        applicationRepository.countByCandidateAndStatus(
                user,
                ApplicationStatus.SHORTLISTED
        );

long rejected =
        applicationRepository.countByCandidateAndStatus(
                user,
                ApplicationStatus.REJECTED
        );

long hired =
        applicationRepository.countByCandidateAndStatus(
                user,
                ApplicationStatus.HIRED
        );

return CandidateDashboardResponse.builder()
        .appliedJobs(applications)
        .shortlistedJobs(shortlisted)
        .rejectedJobs(rejected)
        .hiredJobs(hired)
        .build();
    }
}