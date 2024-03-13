package com.igor.gestao_vagas.modules.candidate.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igor.gestao_vagas.modules.candidate.CandidateEntity;
import com.igor.gestao_vagas.modules.candidate.service.CandidateService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    // Injeção de dependência por meio do construtor
    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping("/")
    public ResponseEntity<Object> createCand(@Valid @RequestBody CandidateEntity candidateEntity) {
        try {
            var result =  this.candidateService.executeCreateCand(candidateEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
