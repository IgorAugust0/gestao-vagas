package com.igor.gestao_vagas.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // gera um construtor com todos os atributos da classe
public class ErrorMessageDTO {
    private String message;
    private String field;
}
