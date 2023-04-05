package br.com.lucas.Container.Store.repository;

import br.com.lucas.Container.Store.entity.Cliente;
import br.com.lucas.Container.Store.util.ClienteCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class Cliente_repositoryTest {
    @Autowired
    Cliente_repository clienteRepository;

    @Test
    void save_InsertClientWhenSuccessful(){
        Cliente clienteTobeSaved = ClienteCreator.createClienteTobeSaved();
        Cliente ClienteSaved = this.clienteRepository.save(clienteTobeSaved);
        Assertions.assertThat(ClienteSaved).isNotNull();
        Assertions.assertThat(ClienteSaved.getId()).isNotNull();
        Assertions.assertThat(ClienteSaved.getCpf()).isEqualTo(clienteTobeSaved.getCpf());
        Assertions.assertThat(ClienteSaved.getName()).isEqualTo(clienteTobeSaved.getName());
        Assertions.assertThat(ClienteSaved.getPassword()).isEqualTo(clienteTobeSaved.getPassword());
        Assertions.assertThat(ClienteSaved.getEmail()).isEqualTo(clienteTobeSaved.getEmail());
    }
    @Test
    void update_UpdateClientWhenSuccessful(){
        Cliente clienteTobeSaved = ClienteCreator.createClienteTobeSaved();
        Cliente ClienteSaved = this.clienteRepository.save(clienteTobeSaved);
        ClienteSaved.setName("dudumalah");
        ClienteSaved.setCpf("56690552098");
        ClienteSaved.setEmail("dudumalah@gmail.com");
        ClienteSaved.setPassword("dudu1234559");

        Cliente ClienteUpdated = this.clienteRepository.save(ClienteSaved);

        Assertions.assertThat(ClienteUpdated).isNotNull();
        Assertions.assertThat(ClienteUpdated.getId()).isNotNull();
        Assertions.assertThat(ClienteUpdated.getCpf()).isEqualTo(clienteTobeSaved.getCpf());
        Assertions.assertThat(ClienteUpdated.getName()).isEqualTo(clienteTobeSaved.getName());
        Assertions.assertThat(ClienteUpdated.getPassword()).isEqualTo(clienteTobeSaved.getPassword());
        Assertions.assertThat(ClienteUpdated.getEmail()).isEqualTo(clienteTobeSaved.getEmail());
    }
    @Test
    void delete_UpdateClientWhenSuccessful(){
        Cliente clienteTobeSaved = ClienteCreator.createClienteTobeSaved();
        Cliente ClienteSaved = this.clienteRepository.save(clienteTobeSaved);

        this.clienteRepository.delete(clienteTobeSaved);

        Optional<Cliente> ClienteOptional = this.clienteRepository.findById(clienteTobeSaved.getId());

        Assertions.assertThat(ClienteOptional.isEmpty());
    }
    @Test
    void FindbyID_UpdateClientWhenSuccessful(){
        Cliente clienteTobeSaved = ClienteCreator.createClienteTobeSaved();
        Cliente ClienteSaved = this.clienteRepository.save(clienteTobeSaved);


        Optional<Cliente> clientes = this.clienteRepository.findById(ClienteSaved.getId());

        Assertions.assertThat(clientes.isEmpty());
        Assertions.assertThat(clientes).contains(ClienteSaved);
    }

}