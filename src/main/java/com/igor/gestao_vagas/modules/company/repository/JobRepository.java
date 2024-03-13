package com.igor.gestao_vagas.modules.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igor.gestao_vagas.modules.company.entity.JobEntity;

import java.util.UUID;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {
}
