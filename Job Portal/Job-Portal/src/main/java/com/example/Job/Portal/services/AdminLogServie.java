package com.example.Job.Portal.services;

import com.example.Job.Portal.entity.AdminLog;
import com.example.Job.Portal.entity.User;
import com.example.Job.Portal.repositories.AdminLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminLogServie {
    private final AdminLogRepository adminLogRepository;

    public AdminLog saveLog(User admin, String action) {
        AdminLog log = new AdminLog();
        log.setAdmin(admin);
        log.setAction(action);
        return adminLogRepository.save(log);
    }

    public List<AdminLog> getLogsByAdmin(User admin) {
        return adminLogRepository.findByAdmin(admin);
    }

    public List<AdminLog> searchLogs(String keyword) {
        return adminLogRepository.findByActionContainingIgnoreCase(keyword);
    }
}
