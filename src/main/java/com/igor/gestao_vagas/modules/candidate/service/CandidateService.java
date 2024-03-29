package com.igor.gestao_vagas.modules.candidate.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.igor.gestao_vagas.exceptions.UserAlreadyExistsException;
import com.igor.gestao_vagas.modules.candidate.CandidateEntity;
import com.igor.gestao_vagas.modules.candidate.repository.CandidateRepository;

import jakarta.validation.Valid;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final PasswordEncoder passwordEncoder;

    public CandidateService(CandidateRepository candidateRepository, PasswordEncoder passwordEncoder) {
        this.candidateRepository = candidateRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // método que executa a criação de um novo candidato, lançando uma exceção caso
    // um candidato com o mesmo username ou email já exista no banco de dados
    public CandidateEntity executeCreateCand(@Valid @RequestBody CandidateEntity candidateEntity) {
        this.candidateRepository
                .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent(existingUser -> {
                    boolean usernameExists = existingUser.getUsername().equals(candidateEntity.getUsername());
                    boolean emailExists = existingUser.getEmail().equals(candidateEntity.getEmail());
                    if (usernameExists) {
                        throw new UserAlreadyExistsException("nome de usuário", candidateEntity.getUsername(),
                                "Candidato");
                    } else if (emailExists) {
                        throw new UserAlreadyExistsException("email", candidateEntity.getEmail(), "Candidato");
                    }
                });

        var password = passwordEncoder.encode(candidateEntity.getPassword());
        candidateEntity.setPassword(password);

        return this.candidateRepository.save(candidateEntity);
    }
}
