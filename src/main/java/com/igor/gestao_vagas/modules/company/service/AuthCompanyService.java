package com.igor.gestao_vagas.modules.company.service;

import javax.security.sasl.AuthenticationException;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.igor.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import com.igor.gestao_vagas.modules.company.repository.CompanyRepository;
import com.igor.gestao_vagas.providers.JWTProvider;

@Service
public class AuthCompanyService {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTProvider jwtProvider;

    public AuthCompanyService(CompanyRepository companyRepository, PasswordEncoder passwordEncoder,
            JWTProvider jwtProvider) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public String executeAuthComp(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("Username/Password incorrect"));

        // Verifica se a senha informada é a mesma que está no banco de dados
        var isPasswordMatch = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());
        if (!isPasswordMatch) { // Se não for, lança uma exceção
            throw new UsernameNotFoundException("Username/Password incorrect");
        }

        String token = this.jwtProvider.generateToken(company.getId().toString());

        return token;
    }
}
