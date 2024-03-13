package com.igor.gestao_vagas.modules.candidate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igor.gestao_vagas.modules.candidate.CandidateEntity;

import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
    // assinatura do método que busca por username ou email, podendo ou não retornar um valor (Optional)
    Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);
}