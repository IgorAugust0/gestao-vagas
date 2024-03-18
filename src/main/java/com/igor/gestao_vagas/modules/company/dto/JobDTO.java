package com.igor.gestao_vagas.modules.company.dto;

import lombok.Data;

/**
 * Essa classe representa um Data Transfer Object (DTO) para a entidade job
 * (vaga).
 * 
 * @Data cria automaticamente os métodos equals, hashCode, toString, getters e
 *       setters.
 * @AllArgsConstructor cria um construtor com todos os argumentos.
 */
@Data
public class JobDTO {

    private String description;
    private String benefits;
    private String level;
}
