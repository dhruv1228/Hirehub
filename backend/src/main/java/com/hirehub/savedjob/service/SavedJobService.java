package com.hirehub.savedjob.service;

import com.hirehub.savedjob.dto.SavedJobResponse;
import java.util.List;

public interface SavedJobService {

    void saveJob(Long jobId);

    void removeJob(Long jobId);

    List<SavedJobResponse> getSavedJobs();
}