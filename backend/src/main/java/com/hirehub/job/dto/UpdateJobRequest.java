package com.hirehub.job.dto;

import com.hirehub.job.enums.JobStatus;
import com.hirehub.job.enums.JobType;
import com.hirehub.job.enums.WorkMode;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateJobRequest {

    private String title;

    private String description;

    private String location;

    private BigDecimal salary;

    private String experience;

    private JobType jobType;

    private WorkMode workMode;

    private JobStatus status;
}