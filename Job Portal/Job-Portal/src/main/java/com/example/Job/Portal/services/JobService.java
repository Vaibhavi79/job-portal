package com.example.Job.Portal.services;

import com.example.Job.Portal.entity.Job;
import com.example.Job.Portal.entity.User;
import com.example.Job.Portal.repositories.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;

    // Create or update job
    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }

    // Get all jobs
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    // Search jobs by title or location
    public List<Job> searchJobs(String keyword) {
        return jobRepository.findByTitleContainingIgnoreCaseOrLocationContainingIgnoreCase(keyword, keyword);
    }

    // Get jobs posted by a recruiter
    public List<Job> getJobsByRecruiter(User recruiter) {
        return jobRepository.findByRecruiter(recruiter);
    }

    // Delete job
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}
