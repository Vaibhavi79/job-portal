package com.example.Job.Portal.repositories;

import com.example.Job.Portal.entity.AdminLog;
import com.example.Job.Portal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminLogRepository extends JpaRepository<AdminLog, Long> {
    List<AdminLog> findByAdmin(User admin);

    // Fetch logs containing keyword
    List<AdminLog> findByActionContainingIgnoreCase(String keyword);
}
