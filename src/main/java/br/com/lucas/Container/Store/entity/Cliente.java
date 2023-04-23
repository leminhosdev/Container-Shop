package br.com.lucas.Container.Store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cliente  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name", nullable = false)
    @Pattern(regexp = "[A-Za-z]+")
    private String name;
    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "invalid Email")
    private String email;
    @Column(name = "cpf", nullable = false, unique = true)
    @CPF(message = "invalid CPF")
    private String cpf;
    @Column(name = "password", nullable = false, length = 100)
    @Size(min = 8, message = "Your password must have minimum 8 digits")
    private String password;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "cliente_nfts",
            joinColumns = { @JoinColumn(name = "cliente_id") },
            inverseJoinColumns = { @JoinColumn(name = "nfts_id") }
    )

    private List<Scrap> nfts = new ArrayList<>();

}