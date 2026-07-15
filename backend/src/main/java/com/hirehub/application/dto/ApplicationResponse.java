package com.hirehub.application.dto;

import java.time.LocalDateTime;

import com.hirehub.application.enums.ApplicationStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationResponse {

    private String companyName;

    private LocalDateTime appliedAt;

    private Long id;

    private Long candidateId;

    private String candidateName;

    private Long jobId;

    private String jobTitle;

    private ApplicationStatus status;

    private String resumeUrl;

    private String coverLetter;
}