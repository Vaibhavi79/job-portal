package com.example.Job.Portal.entity;

import com.example.Job.Portal.dto.JobDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="jobs")
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String location;
    private String company;

    private LocalDate postedDate = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "recruiter_id")
    private User recruiter;
}