package com.igor.gestao_vagas.modules.candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CandidateEntity {
    
    private UUID id;
    private String name;

    @Length(min = 4, max = 50, message = "O nome deve ter entre 4 e 50 caracteres")
    @Pattern(regexp = "^(?!\\s*$).+", message = "O campo usuário não deve conter espaços")
    private String username;

    @Email(message = "O campo deve conter um email válido")
    private String email;

    @Length(min = 8, max = 128, message = "A senha deve ter entre 8 a 128 caracteres")
    // @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$", message = "A senha deve conter pelo menos uma letra minúscula, uma letra maiúscula, um número e um caractere especial")
    private String password;
    
    private String description;
    private String curriculum;
    
}

