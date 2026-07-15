package com.hirehub.profile.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateProfileResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String profilePicture;

    private String bio;

    private String skills;

    private String education;

    private String experience;

    private String linkedin;

    private String github;

    private String portfolio;

    private String location;
}