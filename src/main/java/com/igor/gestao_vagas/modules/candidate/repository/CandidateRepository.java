package com.igor.gestao_vagas.modules.candidate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.igor.gestao_vagas.modules.candidate.CandidateEntity;

import java.util.Optional;
import java.util.UUID;

/**
 * Interface que estende de JpaRepository, que é uma interface de repositório do
 * Spring Data JPA, que já fornece métodos prontos para fazer operações no banco
 * de dados. Contém apenas assinatura dos métodos
 * 
 * Optional é uma classe do pacote java.util que serve para encapsular um
 * retorno de método, indicando que o valor retornado pode ser nulo.
 */
public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
    Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);

    Optional<CandidateEntity> findByUsername(String username);
}