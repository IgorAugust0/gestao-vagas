package com.igor.gestao_vagas.modules.company.service;

import java.time.Instant;
import java.util.Date;

import javax.security.sasl.AuthenticationException;

// import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.igor.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import com.igor.gestao_vagas.modules.company.repository.CompanyRepository;
import com.igor.gestao_vagas.modules.security.SecretKeyGenerator;

@Service
public class AuthCompanyService {

    // caso queira usar a chave secreta do properties (fixa)
    // @Value("${security.token.secret}")
    // private String secretKey;

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthCompanyService(CompanyRepository companyRepository, PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String executeAuthComp(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("Username/Password incorrect"));

        // Verifica se a senha informada é a mesma que está no banco de dados
        var isPasswordMatch = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());
        if (!isPasswordMatch) { // Se não for, lança uma exceção
            throw new UsernameNotFoundException("Username/Password incorrect");
        }

        // TODO: usar RSA256
        String secretKey = SecretKeyGenerator.generateSecretKey();
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var token = JWT.create()
                .withIssuer("gestao_vagas")
                .withSubject(company.getId().toString())
                .withExpiresAt(Date.from(Instant.now().plusSeconds(300)))
                .withIssuedAt(Date.from(Instant.now()))
                .sign(algorithm);

        return token;
    }
}
