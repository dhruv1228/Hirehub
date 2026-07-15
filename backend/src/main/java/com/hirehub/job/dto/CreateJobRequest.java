package com.hirehub.job.dto;

import com.hirehub.job.enums.JobType;
import com.hirehub.job.enums.WorkMode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateJobRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String location;

    @NotNull
    private BigDecimal salary;

    @NotBlank
    private String experience;

    @NotNull
    private JobType jobType;

    @NotNull
    private WorkMode workMode;

}