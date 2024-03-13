package com.igor.gestao_vagas.modules.company.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igor.gestao_vagas.modules.company.entity.CompanyEntity;

public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
    // assinatura do método que busca por username ou email, podendo ou não retornar um valor (Optional)
    Optional<CompanyEntity> findByUsernameOrEmail(String username, String email);
}
