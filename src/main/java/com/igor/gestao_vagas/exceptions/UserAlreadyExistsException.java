package com.igor.gestao_vagas.exceptions;

/*
 * Exceção do tipo não checada (unchecked) que é lançada quando um candidato com o mesmo
 * usuário já existe no banco de dados
 */
public class UserAlreadyExistsException extends RuntimeException {
    private final String conflictingField;
    public UserAlreadyExistsException(String conflictingField, String value) {
        super("Candidato com " + conflictingField + " \"" + value + "\" já existente.");
        this.conflictingField = conflictingField;
    }
    public String getConflictingField(){
        return conflictingField;
    }
}
