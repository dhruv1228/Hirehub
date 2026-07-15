package com.hirehub.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyJobRequest {

    @NotNull
    private Long jobId;

    private String resumeUrl;

    private String coverLetter;
}