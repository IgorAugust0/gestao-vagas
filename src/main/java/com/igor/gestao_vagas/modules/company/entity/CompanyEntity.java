package com.igor.gestao_vagas.modules.company.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "company")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank()
    @Length(min = 4, max = 50, message = "O nome deve ter entre 4 e 50 caracteres")
    @Pattern(regexp = "\\S+", message = "O campo usuário não deve conter espaços")
    private String username;

    @Email(message = "O campo deve conter um email válido")
    private String email;

    @Length(min = 8, max = 128, message = "A senha deve ter entre 8 a 128 caracteres")
    // @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$", message = "A senha deve conter pelo menos uma letra minúscula, uma letra maiúscula, um número e um caractere especial")
    private String password;

    private String website;
    private String name;
    private String description;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
