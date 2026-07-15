package com.hirehub.application.entity;

import com.hirehub.application.enums.ApplicationStatus;
import com.hirehub.common.entity.BaseEntity;
import com.hirehub.job.entity.Job;
import com.hirehub.user.entity.User;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "applications")
public class Application extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "candidate_id")
    private User candidate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "job_id")
    private Job job;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ApplicationStatus status = ApplicationStatus.APPLIED;

    private String resumeUrl;

    @Column(length = 2000)
    private String coverLetter;
}