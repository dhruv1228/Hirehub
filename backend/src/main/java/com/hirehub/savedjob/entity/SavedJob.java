package com.hirehub.savedjob.entity;

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
@Table(
    name = "saved_jobs",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "candidate_id",
                "job_id"
        })
    }
)
public class SavedJob extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private User candidate;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;
}