package com.example.Job.Portal.dto;

import com.example.Job.Portal.Enum.ApplicationStatus;

public class ApplicationDTO {
    private Long id;
    private String candidateName;
    private String candidateEmail;
    private String resumeUrl;
    private String jobTitle;
    private String companyName;
    private ApplicationStatus status;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCandidateName() { return candidateName; }
    public void setCandidateName(String candidateName) { this.candidateName = candidateName; }

    public String getCandidateEmail() { return candidateEmail; }
    public void setCandidateEmail(String candidateEmail) { this.candidateEmail = candidateEmail; }

    public String getResumeUrl() { return resumeUrl; }
    public void setResumeUrl(String resumeUrl) { this.resumeUrl = resumeUrl; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }


    public ApplicationStatus getStatus() { return status; }      // Getter for status
    public void setStatus(ApplicationStatus status) { this.status = status; } // Setter for status
}
