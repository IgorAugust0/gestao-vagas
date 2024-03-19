package com.igor.gestao_vagas.modules.company.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.igor.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import com.igor.gestao_vagas.modules.company.service.AuthCompanyService;

@RestController
@RequestMapping("/company")
public class AuthCompanyController {

    private final AuthCompanyService authCompanyService;

    public AuthCompanyController(AuthCompanyService authCompanyService) {
        this.authCompanyService = authCompanyService;
    }

    @PostMapping("/auth")
    public ResponseEntity<Object> auth(@RequestBody AuthCompanyDTO authCompanyDTO) {
        try {
            var result = this.authCompanyService.executeAuthComp(authCompanyDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
