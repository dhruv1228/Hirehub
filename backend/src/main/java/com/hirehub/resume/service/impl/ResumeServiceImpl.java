package com.hirehub.resume.service.impl;

import com.hirehub.common.cloudinary.CloudinaryService;
import com.hirehub.common.cloudinary.CloudinaryUploadResponse;
import com.hirehub.common.security.SecurityUtils;
import com.hirehub.resume.dto.ResumeResponse;
import com.hirehub.resume.entity.Resume;
import com.hirehub.resume.repository.ResumeRepository;
import com.hirehub.resume.service.ResumeService;
import com.hirehub.user.entity.User;
import com.hirehub.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.hirehub.common.cloudinary.CloudinaryUploadResponse;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;
    private final CloudinaryService cloudinaryService;

    @Override
    public ResumeResponse uploadResume(MultipartFile file) {

        try {

            String email = SecurityUtils.getCurrentUserEmail();

            User user = userRepository.findByEmail(email)
                    .orElseThrow(() ->
                            new RuntimeException("User not found"));

            CloudinaryUploadResponse upload =
        cloudinaryService.uploadFile(file);

            Resume resume = resumeRepository.findByCandidate(user)
                    .orElse(Resume.builder()
                            .candidate(user)
                            .build());

            resume.setResumeUrl(upload.getUrl());
                resume.setPublicId(upload.getPublicId());
                resume.setFileName(file.getOriginalFilename());

            Resume saved = resumeRepository.save(resume);

            return ResumeResponse.builder()
                    .id(saved.getId())
                    .resumeUrl(saved.getResumeUrl())
                    .fileName(saved.getFileName())
                    .build();

        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public ResumeResponse getMyResume() {

        String email = SecurityUtils.getCurrentUserEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Resume resume = resumeRepository.findByCandidate(user)
                .orElseThrow(() ->
                        new RuntimeException("Resume not found"));

        return ResumeResponse.builder()
                .id(resume.getId())
                .resumeUrl(resume.getResumeUrl())
                .fileName(resume.getFileName())
                .build();
    }

    @Override
    public void deleteResume() {

        String email = SecurityUtils.getCurrentUserEmail();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Resume resume = resumeRepository.findByCandidate(user)
                .orElseThrow(() ->
                        new RuntimeException("Resume not found"));

        resumeRepository.delete(resume);
    }
}