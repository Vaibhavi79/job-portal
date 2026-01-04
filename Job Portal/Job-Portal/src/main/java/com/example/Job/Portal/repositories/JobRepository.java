package com.example.Job.Portal.repositories;

import com.example.Job.Portal.entity.Job;
import com.example.Job.Portal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface JobRepository   extends JpaRepository<Job,Long> {
    List<Job> findByTitleContainingIgnoreCaseOrLocationContainingIgnoreCase(String title, String location);

    // Get all jobs by recruiter
    List<Job> findByRecruiter(User recruiter);
}
