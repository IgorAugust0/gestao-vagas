package com.igor.gestao_vagas.modules.candidate.controllers;

// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igor.gestao_vagas.exceptions.UserAlreadyExistsException;
import com.igor.gestao_vagas.modules.candidate.CandidateEntity;
import com.igor.gestao_vagas.modules.candidate.CandidateRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    // Injeção de dependência por meio do construtor
    private final CandidateRepository candidateRepository;

    public CandidateController(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @PostMapping("/")
    public CandidateEntity createCand(@Valid @RequestBody CandidateEntity candidateEntity) {
        this.candidateRepository
                .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent(user -> {
                    throw new UserAlreadyExistsException(candidateEntity.getUsername());
                });
        return this.candidateRepository.save(candidateEntity);
    }
}
