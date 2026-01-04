package com.example.Job.Portal.controllers;

import com.example.Job.Portal.Enum.ApplicationStatus;
import com.example.Job.Portal.dto.ApplicationDTO;
import com.example.Job.Portal.entity.Application;
import com.example.Job.Portal.entity.Job;
import com.example.Job.Portal.entity.User;
import com.example.Job.Portal.repositories.ApplicationRepository;
import com.example.Job.Portal.repositories.JobRepository;
import com.example.Job.Portal.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;

    // APPLY TO JOB
    /*@PostMapping("/apply/{jobId}")
    public ApplicationDTO applyToJob(@PathVariable Long jobId,
                                     @RequestBody ApplicationDTO applicationDTO,
                                     Authentication authentication) {
        String candidateName = authentication.getName();
        User candidate = userRepository.findByUsername(candidateName)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        Application application = new Application();
        application.setCandidate(candidate);
        application.setJob(job);
        application.setStatus(ApplicationStatus.valueOf(applicationDTO.getResumeUrl()));

        applicationRepository.save(application);

        applicationDTO.setId(application.getId());
        applicationDTO.setCandidateName(candidate.getUsername());
        applicationDTO.setCandidateEmail(candidate.getEmail());
        applicationDTO.setJobTitle(job.getTitle());
        applicationDTO.setCompanyName(job.getCompany());

        return applicationDTO;
    }
}*/
    @PostMapping("/apply/{jobId}")
    public ApplicationDTO applyToJob(@PathVariable Long jobId,
                                     @RequestBody ApplicationDTO applicationDTO,
                                     Authentication authentication) {
        String candidateName = authentication.getName();
        User candidate = userRepository.findByUsername(candidateName)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        Application application = new Application();
        application.setCandidate(candidate);
        application.setJob(job);
        application.setStatus(ApplicationStatus.PENDING); // Automatically set PENDING
        application.setResumeUrl(applicationDTO.getResumeUrl()); // Optional actual resume link

        applicationRepository.save(application);

        // Prepare DTO to return
        applicationDTO.setId(application.getId());
        applicationDTO.setCandidateName(candidate.getUsername());
        applicationDTO.setCandidateEmail(candidate.getEmail());
        applicationDTO.setJobTitle(job.getTitle());
        applicationDTO.setCompanyName(job.getCompany());
        applicationDTO.setStatus(application.getStatus()); // Include status

        return applicationDTO;
    }

    //  Recruiter updates the application status
    @PutMapping("/{applicationId}/status")
    public ApplicationDTO updateApplicationStatus(@PathVariable Long applicationId,
                                                  @RequestParam ApplicationStatus status,
                                                  Principal principal) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        // Check if logged-in user is the recruiter of this job
        if (!application.getJob().getRecruiter().getUsername().equals(principal.getName())) {
            throw new RuntimeException("Not authorized to update this application");
        }

        application.setStatus(status); // APPROVED or REJECTED
        applicationRepository.save(application);

        // Map to DTO
        ApplicationDTO applicationDTO = new ApplicationDTO();
        applicationDTO.setId(application.getId());
        applicationDTO.setCandidateName(application.getCandidate().getUsername());
        applicationDTO.setCandidateEmail(application.getCandidate().getEmail());
        applicationDTO.setJobTitle(application.getJob().getTitle());
        applicationDTO.setCompanyName(application.getJob().getCompany());
        applicationDTO.setResumeUrl(application.getResumeUrl());
        applicationDTO.setStatus(application.getStatus());

        return applicationDTO;
    }
}