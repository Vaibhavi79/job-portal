package com.example.Job.Portal.controllers;

import com.example.Job.Portal.dto.JobDTO;
import com.example.Job.Portal.entity.Job;
import com.example.Job.Portal.entity.User;
import com.example.Job.Portal.repositories.JobRepository;
import com.example.Job.Portal.repositories.UserRepository;
import com.example.Job.Portal.security.JwtUtil;
import com.example.Job.Portal.services.JobService;
import com.example.Job.Portal.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@RestController
@RequestMapping("/recruiter")
public class JobController {
    private final JobService jobService;
    private final UserService userService;

    // 🔹 Create a new job
    @PostMapping("/jobs")
    public ResponseEntity<Job> createJob(@RequestBody Job job, Principal principal) {
        User recruiter = userService.getUserByUsername(principal.getName()).orElseThrow();
        job.setRecruiter(recruiter);
        return ResponseEntity.ok(jobService.saveJob(job));
    }

    // 🔹 Update an existing job
    @PutMapping("/jobs/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job jobRequest, Principal principal) {
        User recruiter = userService.getUserByUsername(principal.getName()).orElseThrow();
        Job job = jobService.getAllJobs().stream().filter(j -> j.getId().equals(id)).findFirst().orElseThrow();

        if (!job.getRecruiter().getId().equals(recruiter.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        job.setTitle(jobRequest.getTitle());
        job.setDescription(jobRequest.getDescription());
        job.setLocation(jobRequest.getLocation());
        job.setCompany(jobRequest.getCompany());

        return ResponseEntity.ok(jobService.saveJob(job));
    }

    // 🔹 Delete a job
    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable Long id, Principal principal) {
        User recruiter = userService.getUserByUsername(principal.getName()).orElseThrow();
        Job job = jobService.getAllJobs().stream().filter(j -> j.getId().equals(id)).findFirst().orElseThrow();

        if (!job.getRecruiter().getId().equals(recruiter.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        jobService.deleteJob(id);
        return ResponseEntity.ok("Job deleted successfully");
    }

    // 🔹 Get jobs posted by recruiter
    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> getJobsByRecruiter(Principal principal) {
        User recruiter = userService.getUserByUsername(principal.getName()).orElseThrow();
        return ResponseEntity.ok(jobService.getJobsByRecruiter(recruiter));
    }

}
