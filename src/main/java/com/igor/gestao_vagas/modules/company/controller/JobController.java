package com.igor.gestao_vagas.modules.company.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igor.gestao_vagas.modules.company.entity.JobEntity;
import com.igor.gestao_vagas.modules.company.service.JobService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/")
    public ResponseEntity<Object> createJob(@Valid @RequestBody JobEntity jobEntity) {
        try {
            var createdJob = this.jobService.executeCreateJob(jobEntity);
            return ResponseEntity.ok().body(createdJob);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
