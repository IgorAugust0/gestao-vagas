package com.igor.gestao_vagas.modules.candidate.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igor.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import com.igor.gestao_vagas.modules.candidate.service.AuthCandidateService;

@RestController
@RequestMapping("/candidate")
public class AuthCandidateController {

    private final AuthCandidateService authCandidateService;

    public AuthCandidateController(AuthCandidateService authCandidateService) {
        this.authCandidateService = authCandidateService;
    }

    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO) {
        try {
            var token = this.authCandidateService.executeAuthCand(authCandidateRequestDTO);
            return ResponseEntity.ok().body(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
