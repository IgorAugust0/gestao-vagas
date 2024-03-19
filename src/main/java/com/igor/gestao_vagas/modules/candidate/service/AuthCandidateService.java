package com.igor.gestao_vagas.modules.candidate.service;

import java.util.Arrays;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.igor.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import com.igor.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import com.igor.gestao_vagas.modules.candidate.repository.CandidateRepository;
import com.igor.gestao_vagas.providers.JWTProvider;

public class AuthCandidateService {

    private final CandidateRepository candidateRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTProvider jwtProvider;

    public AuthCandidateService(CandidateRepository candidateRepository, PasswordEncoder passwordEncoder,
            JWTProvider jwtProvider) {
        this.candidateRepository = candidateRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public AuthCandidateResponseDTO executeAuthCand(AuthCandidateRequestDTO authCandidateRequestDTO) {
        var candidate = this.candidateRepository
                .findByUsername(authCandidateRequestDTO.username())
                .orElseThrow(() -> new UsernameNotFoundException("Username/Password incorrect"));

        var isPasswordMatch = this.passwordEncoder.matches(authCandidateRequestDTO.password(), candidate.getPassword());
        if (!isPasswordMatch) {
            throw new UsernameNotFoundException("Username/Password incorrect");
        }

        String token = this.jwtProvider.generateToken(candidate.getId().toString(), "roles",
                Arrays.asList("candidate"));

        var authCandidateResponse = AuthCandidateResponseDTO
                .builder()
                .access_token(token)
                .build();

        return authCandidateResponse;
    }
}
