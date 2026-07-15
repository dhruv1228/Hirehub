package com.hirehub.savedjob.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SavedJobResponse {

    private Long id;

    private Long jobId;

    private String title;

    private String company;

    private String location;

    private String workMode;
}