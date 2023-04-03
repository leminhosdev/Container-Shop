package br.com.lucas.Container.Store.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GeneratorType;

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
    private String name;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "cpf", nullable = false)
    private String cpf;
    @Column(name = "password", nullable = false)
    private String password;
}
