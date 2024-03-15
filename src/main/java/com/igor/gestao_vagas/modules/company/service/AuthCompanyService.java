package com.igor.gestao_vagas.modules.company.service;

import javax.security.sasl.AuthenticationException;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.igor.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import com.igor.gestao_vagas.modules.company.repository.CompanyRepository;

@Service
public class AuthCompanyService {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthCompanyService(CompanyRepository companyRepository, PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void executeAuthComp(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
            () -> {
                throw new UsernameNotFoundException("Company not found");
            }
        );
        var isPasswordMatch = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());
        if (!isPasswordMatch) {
            throw new AuthenticationException();
        }
    }
}
