package com.hirehub.profile.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateProfileRequest {

    @Size(max = 1000)
    private String bio;

    @Size(max = 1000)
    private String skills;

    @Size(max = 1000)
    private String education;

    @Size(max = 1000)
    private String experience;

    private String linkedin;

    private String github;

    private String portfolio;

    private String location;
}