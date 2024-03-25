package com.igor.gestao_vagas.modules.candidate.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igor.gestao_vagas.modules.candidate.CandidateEntity;
import com.igor.gestao_vagas.modules.candidate.service.CandidateService;
import com.igor.gestao_vagas.modules.candidate.service.ProfileCandidateService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    // Injeção de dependência por meio do construtor
    private final CandidateService candidateService;
    private final ProfileCandidateService profileCandidateService;

    public CandidateController(CandidateService candidateService, ProfileCandidateService profileCandidateService) {
        this.candidateService = candidateService;
        this.profileCandidateService = profileCandidateService;
    }

    @PostMapping("/")
    public ResponseEntity<Object> createCand(@Valid @RequestBody CandidateEntity candidateEntity) {
        try {
            var result = this.candidateService.executeCreateCand(candidateEntity);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> getCandProfile(HttpServletRequest request) {
        var candidateId = request.getAttribute("candidate_id");

        try {
            UUID uuid = UUID.fromString(candidateId.toString());
            var profile = this.profileCandidateService.executeProfCand(uuid);
            return ResponseEntity.ok().body(profile);
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
