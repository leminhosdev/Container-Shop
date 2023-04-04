package br.com.lucas.Container.Store.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.validator.constraints.br.CPF;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name", nullable = false)
    @Pattern(regexp = "[A-Za-z]+")
    private String name;
    @Column(name = "email", nullable = false)
    @Email(message = "invalid Email")
    private String email;
    @Column(name = "cpf", nullable = false)
    @CPF(message = "invalid CPF")
    private String cpf;
    @Column(name = "password", nullable = false, length = 100)
    @Size(min = 8, message = "Your password must have minimum 8 digits")
    private String password;
}
