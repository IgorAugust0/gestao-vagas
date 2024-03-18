package com.igor.gestao_vagas.modules.company.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igor.gestao_vagas.modules.company.dto.JobDTO;
import com.igor.gestao_vagas.modules.company.entity.JobEntity;
import com.igor.gestao_vagas.modules.company.service.JobService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/job")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/")
    public ResponseEntity<Object> createJob(@Valid @RequestBody JobDTO jobDTO, HttpServletRequest request) {
        try {
            var companyId = request.getAttribute("company_id");

            var jobEntity = JobEntity.builder()
                    .companyId(UUID.fromString(companyId.toString()))
                    .benefits(jobDTO.getBenefits())
                    .description(jobDTO.getDescription())
                    .level(jobDTO.getLevel())
                    .build();

            var createdJob = this.jobService.executeCreateJob(jobEntity);

            return ResponseEntity.ok().body(createdJob);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            System.err.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }

}
