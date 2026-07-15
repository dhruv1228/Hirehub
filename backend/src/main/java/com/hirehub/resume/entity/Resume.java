package com.hirehub.resume.entity;

import com.hirehub.common.entity.BaseEntity;
import com.hirehub.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "resumes")
public class Resume extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private User candidate;

    @Column(nullable = false)
    private String resumeUrl;

    @Column(nullable = false)
    private String publicId;

    private String fileName;
}