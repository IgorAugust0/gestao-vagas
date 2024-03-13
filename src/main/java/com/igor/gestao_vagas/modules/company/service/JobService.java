package com.igor.gestao_vagas.modules.company.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.igor.gestao_vagas.modules.company.entity.JobEntity;
import com.igor.gestao_vagas.modules.company.repository.JobRepository;

import jakarta.validation.Valid;

@Service
public class JobService {
    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository){
        this.jobRepository = jobRepository;
    }

    public JobEntity executeCreateJob(@Valid @RequestBody JobEntity jobEntity) {
        return this.jobRepository.save(jobEntity);
    }
}
