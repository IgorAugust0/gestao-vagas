package com.igor.gestao_vagas.modules.company.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.igor.gestao_vagas.exceptions.UserAlreadyExistsException;
import com.igor.gestao_vagas.modules.company.entity.CompanyEntity;
import com.igor.gestao_vagas.modules.company.repository.CompanyRepository;

import jakarta.validation.Valid;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyEntity executeCreateCompany(@Valid @RequestBody CompanyEntity companyEntity) {
        this.companyRepository
                .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent(existingCompany -> {
                    boolean usernameExists = existingCompany.getUsername().equals(companyEntity.getUsername());
                    boolean emailExists = existingCompany.getEmail().equals(companyEntity.getEmail());
                    if (usernameExists) {
                        throw new UserAlreadyExistsException("nome de usuário", companyEntity.getUsername(), "Empresa");
                    } else if (emailExists) {
                        throw new UserAlreadyExistsException("email", companyEntity.getEmail(), "Empresa");
                    }
                });
        return this.companyRepository.save(companyEntity);
    }
}
