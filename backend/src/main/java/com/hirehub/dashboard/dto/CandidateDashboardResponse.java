package com.hirehub.dashboard.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDashboardResponse {

    private long appliedJobs;

    private long shortlistedJobs;

    private long rejectedJobs;

    private long hiredJobs;
}