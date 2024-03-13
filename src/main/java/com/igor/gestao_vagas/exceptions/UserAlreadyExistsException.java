package com.igor.gestao_vagas.exceptions;

/*
 * Exceção do tipo não checada (unchecked) que é lançada quando um candidato com o mesmo
 * usuário já existe no banco de dados.
 */
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String username) {
        super("Candidato com nome de usuario \"" + username + "\" ja existente.");
    }
}
