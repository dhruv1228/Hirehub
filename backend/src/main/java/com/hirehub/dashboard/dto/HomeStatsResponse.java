package com.hirehub.dashboard.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HomeStatsResponse {

    private long jobs;
    private long companies;
    private long candidates;
    private long recruiters;
}