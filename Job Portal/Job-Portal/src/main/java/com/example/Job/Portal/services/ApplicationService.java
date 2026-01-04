package com.example.Job.Portal.services;

import com.example.Job.Portal.Enum.ApplicationStatus;
import com.example.Job.Portal.entity.Application;
import com.example.Job.Portal.entity.Job;
import com.example.Job.Portal.entity.User;
import com.example.Job.Portal.repositories.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicationService {
    private final ApplicationRepository applicationRepository;

    // Apply for a job
    public Application applyJob(User candidate, Job job) {
        Optional<Application> existing = applicationRepository.findByCandidateAndJob(candidate, job);
        if (existing.isPresent()) {
            throw new RuntimeException("Already applied to this job!");
        }
        Application application = new Application();
        application.setCandidate(candidate);
        application.setJob(job);
        application.setStatus(ApplicationStatus.PENDING);
        return applicationRepository.save(application);
    }

    // Get applications by candidate
    public List<Application> getApplicationsByCandidate(User candidate) {
        return applicationRepository.findByCandidate(candidate);
    }

    // Get applications for a job
    public List<Application> getApplicationsForJob(Job job) {
        return applicationRepository.findByJob(job);
    }

    // Update application status
    public Application updateApplicationStatus(Long appId, ApplicationStatus status) {
        Application application = applicationRepository.findById(appId)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        application.setStatus(status);
        return applicationRepository.save(application);
    }
}
