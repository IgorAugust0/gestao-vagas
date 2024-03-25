package com.igor.gestao_vagas.modules.candidate.service;

import java.util.UUID;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.igor.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import com.igor.gestao_vagas.modules.candidate.repository.CandidateRepository;

@Service
public class ProfileCandidateService {

    private final CandidateRepository candidateRepository;

    public ProfileCandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public ProfileCandidateResponseDTO executeProfCand(UUID candidateId) {
        var candidate = candidateRepository
                .findById(candidateId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        var profileCandResponseDTO = ProfileCandidateResponseDTO
                .builder()
                .id(candidate.getId())
                .name(candidate.getName())
                .email(candidate.getEmail())
                .username(candidate.getUsername())
                .description(candidate.getDescription())
                .build();

        return profileCandResponseDTO;
    }
}
