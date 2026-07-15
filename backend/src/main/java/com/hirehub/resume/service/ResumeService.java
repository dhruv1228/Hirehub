package com.hirehub.resume.service;

import com.hirehub.resume.dto.ResumeResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ResumeService {

    ResumeResponse uploadResume(MultipartFile file);

    ResumeResponse getMyResume();

    void deleteResume();
}