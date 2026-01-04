package com.example.Job.Portal.repositories;

import com.example.Job.Portal.entity.Application;
import com.example.Job.Portal.entity.Job;
import com.example.Job.Portal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository  extends JpaRepository<Application, Long> {
    List<Application> findByCandidate(User candidate);

    // Get all applications for a job
    List<Application> findByJob(Job job);

    // Check if a candidate has already applied to a job
    Optional<Application> findByCandidateAndJob(User candidate, Job job);
}
