package com.igor.gestao_vagas.exceptions;

/*
 * Exceção do tipo não checada (unchecked) que é lançada quando um candidato com o mesmo
 * usuário já existe no banco de dados
 */
public class UserAlreadyExistsException extends RuntimeException {
    private final String conflictingField;
    private final String userType;
    
    public UserAlreadyExistsException(String conflictingField, String value, String userType) {
        super(userType + " com " + conflictingField + " \"" + value + "\" já existente.");
        this.conflictingField = conflictingField;
        this.userType = userType;
    }
    
    public String getConflictingField(){
        return conflictingField;
    }
    
    public String getUserType(){
        return userType;
    }
}
