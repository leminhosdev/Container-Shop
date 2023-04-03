package br.com.lucas.Container.Store.http.controler.dto.filter;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientFilter {
    private Long id;
    private String name;
    private String email;
    private String cpf;
    private String password;


}
