package com.igor.gestao_vagas.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Essa classe representa um Data Transfer Object (DTO) para autenticação de uma
 * empresa.
 * 
 * @Data cria automaticamente os métodos equals, hashCode, toString, getters e
 *       setters.
 * @AllArgsConstructor cria um construtor com todos os argumentos.
 */
@Data
@AllArgsConstructor
public class AuthCompanyDTO {

    private String username;
    private String password;
}
