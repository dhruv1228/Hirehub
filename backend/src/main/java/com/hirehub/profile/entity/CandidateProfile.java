package com.hirehub.profile.entity;

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
@Table(name = "candidate_profiles")
public class CandidateProfile extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "candidate_id", nullable = false, unique = true)
    private User candidate;

    @Column(length = 1000)
    private String bio;

    @Column(length = 1000)
    private String skills;

    @Column(length = 1000)
    private String education;

    @Column(length = 1000)
    private String experience;

    private String linkedin;

    private String github;

    private String portfolio;

    private String location;
}