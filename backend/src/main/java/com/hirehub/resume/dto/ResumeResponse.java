package com.hirehub.resume.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResumeResponse {

    private Long id;

    private String resumeUrl;

    private String fileName;
}